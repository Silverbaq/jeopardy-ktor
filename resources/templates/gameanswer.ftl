<#import "common/bootstrap.ftl" as b>

<@b.page>
    <script src=/static/main.js></script>
    <div class="row">
        Answer:<br>
        <h2>${answer.answer}</h2>
    </div>
    <div class="row">
        Question:<br>
        <h2>${answer.question}</h2>
    </div>
    <#list teams as team>
    <div class="row">
        <p>Team: ${team.name}</p>
        <form method="post" action="/gameanswer">
            <input type="hidden" name="teamId" value="${team.id}">
            <input type="hidden" name="answerId" value="${answer.id}">
            <input type="hidden" name="action" value="correct">
            <input type="submit" value="Correct">
        </form>

        <form method="post" action="/gameanswer">
            <input type="hidden" name="teamId" value="${team.id}">
            <input type="hidden" name="answerId" value="${answer.id}">
            <input type="hidden" name="action" value="wrong">
            <input type="submit" value="Wrong">
        </form>
    </div>
    </#list>

</@b.page>