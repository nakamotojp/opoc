
//カウンターの初期化
var counter1 = 1;
var counter_ques1 = 0;
var counter_ans1 = 0;


//質問の行を追加
function AddTableRows1(question,answer,id,del_b){

	// カウンタを回す
	counter1++;
	counter_ques1++;

	//
	var table1 = document.getElementById(id);
	var row1 = table1.insertRow(counter1);
	var cell1 = row1.insertCell(0);
	var cell2 = row1.insertCell(1);


	// class の付与は UserAgent によって
	// 挙動が違うっぽいので念のため両方の方法で
	cell2.setAttribute("class",question);
	cell2.className = question;

	var HTML1 = '<center>質問</center>';
	var HTML2 = ' <TEXTAREA name="' + question.concat(counter_ques1) + '"  maxlength="200" class="limited" rows="5" cols="50"></TEXTAREA>';
	cell1.innerHTML = HTML1;
	cell2.innerHTML = HTML2;
	
	AddTableRowsAnser1(answer,id);
	
    // 削除ボタンを有効にする
    document.formMain[del_b].disabled=false;

}


//答えの行を追加
function AddTableRowsAnser1(answer,id){

	// カウンタを回す
	counter1++;
	counter_ans1++;


	var table1 = document.getElementById(id);
	var row1 = table1.insertRow(counter1);
	var cell1 = row1.insertCell(0);
	var cell2 = row1.insertCell(1);
	
	cell2.setAttribute("class",answer);
	cell2.className = answer;

	var HTML1 = '<center>回答</center>';
	var HTML2 = '<TEXTAREA name="'+ answer.concat(counter_ans1) + '"  maxlength="200" class="limited" rows="5" cols="50"></TEXTAREA>';
	cell1.innerHTML = HTML1;
	cell2.innerHTML = HTML2;
}


function deleteTable1(question,answer,id,del_b) {
	
	var last_ques1 = document.getElementsByName("rjmq_question".concat(counter_ques1))[0].value;
	var last_ans1 = document.getElementsByName("rjmq_answer".concat(counter_ans1))[0].value;
	if(!(last_ques1 == "" && last_ans1 =="")){
		// 「OK」時の処理開始 ＋ 確認ダイアログの表示
		if(window.confirm('入力した内容も消えてしまいますがよろしいですか？')){
		// 「OK」時の処理終了
		}
		// 「キャンセル」時の処理開始
		else{
	
			return;
	
		}
	}

// 「キャンセル」時の処理終了
	counter_ques1--;
	counter_ans1--;
	var table1 = document.getElementById(id);
    table1.deleteRow(counter1);
    counter1--;
    table1.deleteRow(counter1);
    counter1--;
    // テーブルの数（行の数）が1の場合は
    // 削除ボタンを無効にする
    if(counter1<=1){
    	document.formMain[del_b].disabled=true;
    }

}


function counterPlus1(size){
	 counter1 = size*2-1;
	 counter_ques1 = size-1;
	 counter_ans1 = size-1;
}


