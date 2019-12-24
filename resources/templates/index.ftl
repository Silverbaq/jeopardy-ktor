<#import "common/bootstrap.ftl" as b>

<@b.page>
    <link rel="stylesheet" href="/static/gameboard.css">

    <canvas id='myCanvas' width='800' height='800'></canvas>
    <div id="mainGame" class="container">
        <script src=/static/main.js></script>
        <script src=/static/fireworks.js></script>


        <div class="row" id="teams"></div>
        <div class="row" id="gameboard"></div>
        <div class="row " id="answer"></div>
    </div>

    <div id="takeover" class="container">
        <div class="row justify-content-center" id="takeover-row"></div>
    </div>
</@b.page>