// Global variable to hold the websocket.
var socket = null;

/**
 * This function is in charge of connecting the client.
 */
function connect() {
    // First we create the socket.
    // The socket will be connected automatically asap. Not now but after returning to the event loop,
    // so we can register handlers safely before the connection is performed.
    console.log("Begin connect");
    socket = new WebSocket("ws://" + window.location.host + "/ws");

    // We set a handler that will be executed if the socket has any kind of unexpected error.
    // Since this is a just sample, we only report it at the console instead of making more complex things.
    socket.onerror = function () {
        console.log("socket error");
    };

    // We set a handler upon connection.
    // What this does is to put a text in the messages container notifying about this event.
    socket.onopen = function () {
        console.log("Connected");
    };

    // If the connection was closed gracefully (either normally or with a reason from the server),
    // we have this handler to notify to the user via the messages container.
    // Also we will retry a connection after 5 seconds.
    socket.onclose = function (evt) {
        // Try to gather an explanation about why this was closed.
        var explanation = "";
        if (evt.reason && evt.reason.length > 0) {
            explanation = "reason: " + evt.reason;
        } else {
            explanation = "without a reason specified";
        }

        // Notify the user using the messages container.
        console.log("Disconnected with close code " + evt.code + " and " + explanation);
        // Try to reconnect after 5 seconds.
        setTimeout(connect, 5000);
    };

    // If we receive a message from the server, we want to handle it.
    socket.onmessage = function (event) {
        received(event.data.toString());
    };
}

/**
 * Handle messages received from the sever.
 *
 * @param message The textual message
 */
function received(message) {
    // Out only logic upon message receiving is to output in the messages container to notify the user.
    commandControl(message)
}

function commandControl(input) {
    var obj = JSON.parse(input);
    var cmd = obj["cmd"];
    var data = obj["data"];

    console.log(data)

    switch (cmd) {
        case "BOARD":
            clearDisplay()
            displayTeams(data["teams"])
            generateGameTable(data["categories"])
    }
}

function initLoop() {
    connect();
}

function clearDisplay(){
    document.getElementById("teams").innerHTML = ""
    document.getElementById("gameboard").innerHTML = ""
    document.getElementById("answer").innerHTML = ""
}

function displayTeams(teams) {
    teams.forEach(function (team, index, array) {
        createTeamCard(team.name, team.points);
        console.log(team)
    });
}

function createTeamCard(name, points) {
    var card = document.createElement("div");
    card.className = "card";

    var cardHead = document.createElement("div");
    cardHead.className = "card-header";
    cardHead.textContent = name

    var cardBody = document.createElement("div")
    cardBody.className = "card-body text-center"

    var pointsElement = document.createElement("h5")
    pointsElement.className = "card-title"
    pointsElement.textContent = points

    card.append(cardHead)
    card.append(cardBody)
    cardBody.append(points)

    $("#teams").append(card)
}

function generateGameTable(categories) {
    var table = document.createElement("table")
    table.className = "table table-bordered text-center"

    var tableHead = document.createElement("thead")
    var headerRow = document.createElement("tr")
    tableHead.append(headerRow)
    table.append(tableHead)

    var tableBody = document.createElement("tbody")
    table.append(tableBody)

    var tr1 = document.createElement("tr")
    var tr2 = document.createElement("tr")
    var tr3 = document.createElement("tr")
    var tr4 = document.createElement("tr")
    var tr5 = document.createElement("tr")

    tableBody.append(tr1)
    tableBody.append(tr2)
    tableBody.append(tr3)
    tableBody.append(tr4)
    tableBody.append(tr5)

    categories.forEach(function (value) {
        var th = document.createElement("th")
        th.textContent = value["name"]
        tableHead.append(th)

        var td1 = document.createElement("td")
        var td2 = document.createElement("td")
        var td3 = document.createElement("td")
        var td4 = document.createElement("td")
        var td5 = document.createElement("td")

        if (!value["answers"][0]["done"]) td1.textContent = value["answers"][0]["points"]
        if (!value["answers"][1]["done"]) td2.textContent = value["answers"][1]["points"]
        if (!value["answers"][2]["done"]) td3.textContent = value["answers"][2]["points"]
        if (!value["answers"][3]["done"]) td4.textContent = value["answers"][3]["points"]
        if (!value["answers"][4]["done"]) td5.textContent = value["answers"][4]["points"]

        tr1.append(td1)
        tr2.append(td2)
        tr3.append(td3)
        tr4.append(td4)
        tr5.append(td5)
    })


    $("#gameboard").append(table)
}


// This is the entry point of the client.
initLoop();