<#import "common/bootstrap.ftl" as b>

<@b.page>
    <div class="row">
        <form method="post" action="/gamesetup">
            <input type="hidden" name="action" value="start">
            <input type="submit" value="Run"/>
        </form>
    </div>

    <div class="row">
        <div class="col-md-6">
            <p>Teams:</p>
            <div class="panel-body">
                <form method="post" action="/teams">
                    <input type="hidden" name="action" value="add">
                    Name:<br>
                    <input type="text" name="name"/><br>
                    <input type="submit" value="Submit"/>
                </form>
            </div>

            <#if teams?? && (teams?size > 0)>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list teams as team>
                        <tr>
                            <td style="vertical-align:middle"><h3>${team.name}</h3></td>
                            <td class="col-md-1" style="text-align:center;vertical-align:middle;">
                                <form method="post" action="/teams">
                                    <input type="hidden" name="id" value="${team.id}">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="image" src="/static/delete.png" width="24" height="24" border="0 alt="Delete" />
                                </form>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </#if>

        </div>
        <div class="col-md-6">
            <p>Category:</p>
            <div class="panel-body">
                <form method="post" action="/category">
                    <input type="hidden" name="action" value="add">
                    Name:<br>
                    <input type="text" name="name"/><br>
                    <input type="submit" value="Submit"/>
                </form>
            </div>

            <#if categories?? && (categories?size > 0)>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list categories as category>
                        <tr>
                            <td style="vertical-align:middle"><h3>${category.name}</h3></td>

                            <td class="col-md-1" style="text-align:center;vertical-align:middle;">
                                <a href="/category/${category.id}">Show</a>
                            </td>

                            <td class="col-md-1" style="text-align:center;vertical-align:middle;">
                                <form method="post" action="/category">
                                    <input type="hidden" name="id" value="${category.id}">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="image" src="/static/delete.png" width="24" height="24" border="0 alt="Delete" />
                                </form>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </#if>

        </div>
    </div>
</@b.page>