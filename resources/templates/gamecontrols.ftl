<#import "common/bootstrap.ftl" as b>

<@b.page>
    <script src=/static/main.js></script>
    <div class="container">
        <div class="row">
            <div class="col-3">
                <form method="post" action="/gamecontrols">
                    <input type="hidden" name="action" value="refresh">
                    <input type="submit" value="Refresh"/>
                </form>
            </div>
            <div class="col-3">
                <form method="post" action="/gamecontrols">
                    <input type="hidden" name="action" value="random">
                    <input type="submit" value="Random!"/>
                </form>
            </div>
        </div>
        <br>
        <div class="row">
            <table class="table table-striped">
                <thead>
                <tr>

                </tr>
                </thead>
                <tbody>
                <#list categories as category>
                    <tr>
                        <td style="vertical-align:middle"><h3>${category.name}</h3></td>
                        <#list category.answers as answer>
                            <#if !answer.done>
                                <td class="col-md-1" style="text-align:center;vertical-align:middle;">
                                    <form method="post" action="/gamecontrols">
                                        <input type="hidden" name="id" value="${answer.id}">
                                        <input type="hidden" name="action" value="answer">
                                        <input type="submit" value="${answer.points}">
                                    </form>
                                </td>
                            </#if>
                        </#list>
                    </tr>
                </#list>
                </tbody>
            </table>

        </div>
    </div>
</@b.page>