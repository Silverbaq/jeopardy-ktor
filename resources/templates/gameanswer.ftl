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
        <div class="row form-group">
            <label class="col-2 col-form-label">Team: ${team.name}</label>
            <div class="col-5">
                <form method="post" action="/gameanswer">
                    <input type="hidden" name="teamId" value="${team.id}">
                    <input type="hidden" name="answerId" value="${answer.id}">
                    <input type="hidden" name="action" value="correct">
                    <input type="submit" class="btn btn-success" value="Correct">
                </form>
            </div>
            <div class="col-5">
                <form method="post" action="/gameanswer">
                    <input type="hidden" name="teamId" value="${team.id}">
                    <input type="hidden" name="answerId" value="${answer.id}">
                    <input type="hidden" name="action" value="wrong">
                    <input type="submit" class="btn btn-danger" value="Wrong">
                </form>
            </div>
        </div>
    </#list>

    <div class="row">
        <div class="col-5"><a href="/gamecontrols">To Controls</a></div>
        <div class="col-5">
            <form method="post" action="/gameanswer">
                <input type="hidden" name="teamId" value="${teams[0].id}">
                <input type="hidden" name="answerId" value="${answer.id}">
                <input type="hidden" name="action" value="showanswer">
                <input type="submit" class="btn btn-primary" value="Show answer">
            </form>
        </div>
    </div>

</@b.page>