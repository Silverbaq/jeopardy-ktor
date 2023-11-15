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
                    <input type="hidden" name="action" value="random-image">
                    <input type="submit" value="Random image!"/>
                </form>
            </div>
            <div class="col-3">
                <form method="post" action="/gamecontrols">
                    <input type="hidden" name="action" value="random-video">
                    <input type="submit" value="Random video!"/>
                </form>
            </div>
            <div class="col-3">
                <form method="post" action="/gamecontrols">
                    <input type="hidden" name="action" value="showFinalRound">
                    <input type="submit" value="Show final round"/>
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

        <h2>Adjust points</h2>
        <div class="row">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Team</th>
                    <th>Points</th>
                    <th>Add</th>
                    <th>Sub</th>
                </tr>
                </thead>
                <tbody>
                <#list teams as team>
                    <tr>
                        <td style="vertical-align:middle"><h3>${team.name}</h3></td>
                        <td style="vertical-align:middle"><h3>${team.points}</h3></td>
                        <td class="col-md-1" style="text-align:center;vertical-align:middle;">
                            <form method="post" action="/gamecontrols">
                                <input type="hidden" name="id" value="${team.id}">
                                <input type="hidden" name="action" value="addPoints">
                                <input type="text" name="points">
                                <input type="submit" value="Add points">
                            </form>
                        </td>
                        <td class="col-md-1" style="text-align:center;vertical-align:middle;">
                            <form method="post" action="/gamecontrols">
                                <input type="hidden" name="id" value="${team.id}">
                                <input type="hidden" name="action" value="subPoints">
                                <input type="text" name="points">
                                <input type="submit" value="Sub points">
                            </form>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>

        </div>
    </div>
</@b.page>