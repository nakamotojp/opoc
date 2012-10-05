
var counter = 1;
var counter_ques = 0;
var counter_ans = 0;


//質問の行を追加
function AddTableRows(question,answer,id,del_b){

	// カウンタを回す
	counter++;
	counter_ques++;

	var table1 = document.getElementById(id);
	var row1 = table1.insertRow(counter);
	var cell1 = row1.insertCell(0);
	var cell2 = row1.insertCell(1);

	// class の付与は UserAgent によって
	// 挙動が違うっぽいので念のため両方の方法で
	cell2.setAttribute("class",question);
	cell2.className = question;

	var HTML1 = '<center>質問</center>';
	var HTML2 = ' <TEXTAREA'+ ' id=' + question.concat(counter_ques) + ' name="' + question.concat(counter_ques) + '"  maxlength="200" class="limited" rows="5" cols="50"></TEXTAREA>';
	cell1.innerHTML = HTML1;
	cell2.innerHTML = HTML2;
	
	AddTableRowsAnser(answer,id);

    // 削除ボタンを有効にする
    document.formMain[del_b].disabled=false;
}



//答えの行を追加
function AddTableRowsAnser(answer,id){

	// カウンタを回す
	counter++;
	counter_ans++;


	var table1 = document.getElementById(id);
	var row1 = table1.insertRow(counter);
	var cell1 = row1.insertCell(0);
	var cell2 = row1.insertCell(1);
	
	cell2.setAttribute("class",answer);
	cell2.className = answer;

	var HTML1 = '<center>回答</center>';
	var HTML2 = ' <TEXTAREA'+ ' id=' + answer.concat(counter_ans) + ' name="' + answer.concat(counter_ans) + '" maxlength="200" class="limited" rows="5" cols="50"></TEXTAREA>';
	cell1.innerHTML = HTML1;
	cell2.innerHTML = HTML2;
}



function deleteTable(question,answer,id,del_b) {

	var last_ques = document.getElementsByName("rjmr_rquestion".concat(counter_ques))[0].value;
	var last_ans = document.getElementsByName("rjmr_answer".concat(counter_ans))[0].value;
	if(!(last_ques == "" && last_ans =="")){
		// 「OK」時の処理開始 ＋ 確認ダイアログの表示
		if(window.confirm('入力した内容も消えてしまいますがよろしいですか？')){
		// 「OK」時の処理終了
		}
		// 「キャンセル」時の処理開始
		else{
	
			return;
	
		}
	}

	counter_ques--;
	counter_ans--;
	var table = document.getElementById(id);
    table.deleteRow(counter);
    counter--;
    table.deleteRow(counter);
    counter--;
    // テーブルの数（行の数）が1の場合は
    // 削除ボタンを無効にする
    if(counter<=1){
    	document.formMain[del_b].disabled=true;
    }

}
function deleteTable2(question,answer,id,del_b) {
	var last_ques = document.getElementsByName("roeq_question".concat(counter_ques))[0].value;
	var last_ans = document.getElementsByName("roeq_answer".concat(counter_ans))[0].value;
	if(!(last_ques == "" && last_ans =="")){
		// 「OK」時の処理開始 ＋ 確認ダイアログの表示
		if(window.confirm('入力した内容も消えてしまいますがよろしいですか？')){
		// 「OK」時の処理終了
		}
		// 「キャンセル」時の処理開始
		else{
	
			return;
	
		}
	}

	counter_ques--;
	counter_ans--;
	var table = document.getElementById(id);
    table.deleteRow(counter);
    counter--;
    table.deleteRow(counter);
    counter--;
    // テーブルの数（行の数）が1の場合は
    // 削除ボタンを無効にする
    if(counter<=1){
    	document.formMain[del_b].disabled=true;
    }

}

function counterPlus(size){
	 counter = size*2-1;
	 counter_ques = size-1;
	 counter_ans = size-1;
}

