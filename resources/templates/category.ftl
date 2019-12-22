<#import "common/bootstrap.ftl" as b>

<@b.page>
    <div class="row">
        <div class="col-md-6">
            <h1>${category.name}</h1>
        </div>

        Add Answer:<br>
        <form method="post" action="/answer">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="categoryId" value="${category.id}"/><br>
            Name:
            <input type="text" name="question"/><br>
            Answer:
            <input type="text" name="answer"/><br>
            Points:
            <input type="text" name="points"/><br>
            <input type="submit" value="Submit"/>
        </form>

        <#if category.answers?? && (category.answers?size > 0)>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Name</th>
                </tr>
                </thead>
                <tbody>
                <#list category.answers as answer>
                    <tr>
                        <td style="vertical-align:middle"><h3>${answer.answer}</h3></td>
                        <td class="col-md-1" style="text-align:center;vertical-align:middle;">
                            <form method="post" action="/teams">
                                <input type="hidden" name="id" value="${answer.id}">
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
</@b.page>