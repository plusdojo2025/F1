<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
     <div class="help">
        <h1 class="explain">操作説明について</h1>
       <section class="top">
        <h2 class="topic">TOP</h2>
        <ul class="helpText">
            <li>アカウント情報に登録されている目標や今までの実績が表示されます。</li>
            <li>隙間時間、今の気分、作業ジャンルを入力後、提案を受けるボタンを押してください。入力内容から最適なタスクを提案します。</li>
            <li>上側がユーザーが登録しているタスク、下側が他のユーザーが登録しているタスクです。</li>
            <li>右下にある【タスクの新規作成ボタン】からタスクを新規作成する画面へ遷移することも可能です。</li>
            <li>表示されているタスクの中から1つ選択するとタイマーが動き始めます。<br>
            タスクが完了しましたら、完了ボタンを押してください。</li>
            <li>完了ボタンを押すと結果画面へ遷移します。完了以外のボタンを押しますと、タスクを行っていた記録が削除されてしまいます。</li>
            <li>結果画面ではタスクの実行時間が表示されます。</li>
            <li>満足度を入力し、満足度登録ボタンを押してください。このボタン以外を押すとタスクの満足度は表示されず、記録のみ残ります。</li>

        </ul>
        </section>
        <section class="regist">

        <h2 class="topic">タスクの新規作成</h2>
        <ul class="helpText">
            <li>登録するタスクの内容、所要時間、気分、作業ジャンル、公開設定を入力してください。<br>
                入力が終わりましたら登録ボタンを押してください。</li>
            <li>公開設定にチェックが入っている状態は他のユーザーのタスク提案時に、登録しているタスクも表示される可能性がございます。</li>

        </ul>
        </section>

        <section class="view">
        <h2 class="topic">タスク一覧</h2>
        <ul class="helpText">
            <li>登録したタスクを確認することができます。</li>
            <li>【変更】ボタンを押すと、タスクの内容を変更することができます。</li>
            <li>【削除】ボタンを押すと、タスクを削除することができます。</li>
        </ul>
        </section>

        <section class="record">
        <h2 class="topic">実績</h2>
        <ul class="helpText">
            <li>これまでに行ったタスクの実行記録を見ることができます。</li>
            <li>これまでに活用した合計時間やよく行う気分・作業ジャンルも表示されます。</li>
        </ul>
        </section>
        
        <section class="account">
        <h2 class="topic">アカウント</h2>
        <ul class="helpText">
                <li>【アカウント】を押すとアカウント情報を確認することができます。</li>
                <li>【ログアウト】ボタンを押すとログアウトすることが可能です。</li>
                <li>【内容を変更する】ボタンを押すことでアカウント情報を変更する画面へ遷移します。</li>
                <li>変更内容を入力し【確認】ボタンを押すと確認画面へ遷移します。</li>
                <li>変更内容にお間違いなければ、【この内容で確定】ボタンを押してください。アカウント情報が変更されます。</li>
            </ul>
        </section>
    </div>
    <a href="<c:url value='/TopPageServlet' />" class="light-orange-btn"> TOP画面へ戻る</a>
    
</body>
</html>