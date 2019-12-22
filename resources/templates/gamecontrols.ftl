<#import "common/bootstrap.ftl" as b>

<@b.page>
    <script src=/static/main.js></script>
    <div class="row">
        <form method="post" action="/gamecontrols">
            <input type="hidden" name="action" value="refresh">
            <input type="submit" value="Refresh"/>
        </form>
    </div>
</@b.page>