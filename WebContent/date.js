/**
 * 存在しない日（2月30日など）の選択肢を非表示にする
 *
 * @param yearId 「年」のselect要素のID
 * @param monthId 「月」のselect要素のID
 * @param dayId 「日」のselect要素のID
 */
function nonExistDayIsNonDisplayed(yearId, monthId, dayId) {
  var monthSelect = document.getElementById(monthId);
  var daySelect = document.getElementById(dayId);
  var selectedMonth = parseInt(monthSelect.getElementsByTagName("option")[monthSelect.selectedIndex].value, 10);
  var dayOptions = daySelect.getElementsByTagName("option");

  if (selectedMonth === 2) {
    // 2月の場合
    var yearSelect = document.getElementById(yearId);
    var selectedYear = parseInt(yearSelect.getElementsByTagName("option")[yearSelect.selectedIndex].value, 10);

    for (var i = dayOptions.length - 1; i >= 0; i--) {
      var dayOption = dayOptions[i];
      var dayValue = parseInt(dayOption.value, 10);
      var leapYear = isLeapYear(selectedYear); // 閏年か
      if (dayValue >= 30 || (dayValue === 29 && !leapYear)) {
        dayOption.setAttribute("disabled", "disabled"); // 選択不能指定
        if (dayOption.selected) {
          // 29日(閏年でない場合のみ)、30日、31日のいずれかが選択されていた場合は、2月の最終日に変更
          if (leapYear) {
            daySelect.value = "29";
          } else {
            daySelect.value = "28";
          }
        }
      } else if ("disabled" === dayOption.getAttribute("disabled")) {
        // 選択不能指定が成されていたら解除
        dayOption.removeAttribute("disabled");
      } else {
        break;
      }
    }
  } else if (selectedMonth === 4 || selectedMonth === 6 || selectedMonth === 9 || selectedMonth === 11) {
    // 月の日数が30日の場合
    for (var i = dayOptions.length - 1; i >= 0; i--) {
      var dayOption = dayOptions[i];
      var dayValue = parseInt(dayOption.value, 10);
      if (dayValue >= 31) {
        dayOption.setAttribute("disabled", "disabled"); // 選択不能指定
        if (dayOption.selected) {
          // 31日が選択されていた場合は、各月の最終日に変更
          daySelect.value = "30";
        }
      } else if ("disabled" === dayOption.getAttribute("disabled")) {
        // 選択不能指定が成されていたら解除
        dayOption.removeAttribute("disabled");
      } else {
        break;
      }
    }
  } else {
    // 月の日数が31日の場合
    for (var i = dayOptions.length - 1; i >= 0; i--) {
      var dayOption = dayOptions[i];
      if ("disabled" === dayOption.getAttribute("disabled")) {
        // 選択不能指定が成されていたら解除
        dayOption.removeAttribute("disabled");
      } else {
        break;
      }
    }
  }
}

/**
 * 閏年か
 *
 * @param year 年
 *
 * @return 閏年ならtrue、それ以外の場合はfalse
 */
function isLeapYear(year) {
  var date = new Date(year, 1, 29);
  return date.getMonth() === 1;
}