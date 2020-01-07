<#import "common/bootstrap.ftl" as b>

<@b.page>
    <div class="row">
        <div class="col">
            <h1>${category.name}</h1>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="card mb-2">
                <h3 class="card-header text-white bg-primary">Add Answer</h3>
                <div class="card-body">
                    <div class="row">
                        <div class="col">
                            <form method="post" class="needs-validation" action="/answer">
                                <div class="form-group">
                                    <label for="question">Question</label>
                                    <input type="text" class="form-control" id="question" placeholder="Question"
                                           name="question" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="answer">Answer</label>
                                    <input type="text" class="form-control" id="answer" placeholder="Answer"
                                           name="answer" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="points">Points</label>
                                    <input type="text" class="form-control" id="points" placeholder="points"
                                           name="points" required/>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <input type="hidden" name="action" value="add">
                                <input type="hidden" name="categoryId" value="${category.id}"/>
                                <input type="submit" class="btn btn-success" value="Add"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <#if category.answers?? && (category.answers?size > 0)>
                <div class="card">
                    <h3 class="card-header text-white bg-primary">Answers</h3>
                    <div class="card-body">
                        <table class="table table-striped">
                            <tbody>
                            <#list category.answers as answer>
                                <tr>
                                    <td style="vertical-align:middle"><h3>${answer.answer}</h3></td>
                                    <td class="col-md-1" style="text-align:center;vertical-align:middle;">
                                        <form method="post" action="/category">
                                            <input type="hidden" name="id" value="${answer.id}">
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