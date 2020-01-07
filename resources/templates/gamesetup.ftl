<#import "common/bootstrap.ftl" as b>

<@b.page>
    <div class="row mb-4">
        <div class="col">
            <form method="post" action="/gamesetup">
                <input type="hidden" name="action" value="start">
                <input type="submit" class="btn btn-success btn-block" value="Run"/>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <div class="card mb-2">
                <h3 class="card-header text-white bg-primary">Add Team</h3>
                <div class="card-body">
                    <form method="post" class="form-inline" action="/teams">
                        <div class="row">
                            <div class="col">
                                <input type="text" placeholder="Name" class="form-control" name="name"
                                       value="name"/>
                            </div>
                            <input type="hidden" name="action" value="add">
                            <input type="submit" class="btn btn-success mb-2" value="Add"/>
                        </div>
                    </form>

                </div>
            </div>

            <#if teams?? && (teams?size > 0)>
                <div class="card">
                    <h3 class="card-header text-white bg-primary">Teams</h3>
                    <div class="card-body">
                        <table class="table table-striped">
                            <tbody>
                            <#list teams as team>
                                <tr>
                                    <td style="vertical-align:middle"><h3>${team.name}</h3></td>
                                    <td class="col-md-1" style="text-align:center;vertical-align:middle;">
                                        <form method="post" action="/teams">
                                            <input type="hidden" name="id" value="${team.id}">
                                            <input type="hidden" name="action" value="delete">
                                            <input type="image" src="/static/delete.png" width="24" height="24"
                                                   border="0" alt="Delete"/>
                                        </form>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </#if>

        </div>
        <div class="col-md-6">
            <div class="card mb-2">
                <h3 class="card-header text-white bg-primary">Category</h3>
                <div class="card-body">
                    <form method="post" class="form-inline" action="/category">
                        <input type="hidden" name="action" value="add">
                        <div class="row">
                            <div class="col">
                                <input type="text" name="name"/><br>
                            </div>
                            <div class="col">
                                <input type="submit" class="btn btn-success mb-2" value="Add"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <#if categories?? && (categories?size > 0)>
                <div class="card">
                    <h3 class="card-header text-white bg-primary">Categories</h3>
                    <div class="card-body">
                        <table class="table table-striped">
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
                                            <input type="image" src="/static/delete.png" width="24" height="24"
                                                   border="0" alt="Delete"/>
                                        </form>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </#if>

        </div>
    </div>
</@b.page>