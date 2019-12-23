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
            displayBoard()
            displayTeams(data["teams"])
            generateGameTable(data["categories"])
            break
        case "ANSWER":
            //clearDisplay()
            displayAnswer()
            //displayTeams(data["teams"])
            generateAnswer(data["answer"])
            break
        case "GIVE_POINTS":
            console.log(data)
            revealQuestion()
            giveTeamPoints(data["teamName"], data["points"])
            break
        case "TAKE_POINTS":
            takeTeamPoints(data["teamName"], data["points"])
            break
        case "IMAGE":
            showRandomImage();
            break;
        default:
            console.log("Wrong command!")
    }
}

function initLoop() {
    connect();
}

function takeTeamPoints(name, points) {
    document.getElementById(name + "-body").textContent = points
    //document.getElementById(name+"-points")
}

function giveTeamPoints(name, points) {
    document.getElementById(name + "-body").textContent = points
    //document.getElementById(name+"-points")
}

function showRandomImage() {
    $("#mainGame").hide();
    $("#takeover").show();

    $("#takeover").empty();


    var image = document.createElement("img");
    image.src = getRandomImage();
    $("#takeover").append(image);

    setTimeout(function () {
        $("#takeover").hide();
        $("#mainGame").show()
    }, 3000);
}

function getRandomImage() {
    var myArray = [
        "https://i.kym-cdn.com/entries/icons/original/000/005/220/wizard.jpg",
        "https://i.kym-cdn.com/photos/images/newsfeed/000/117/814/are-you-wizard.jpg"
    ];
    return myArray[Math.floor(Math.random() * myArray.length)];
}

function clearDisplay() {
    document.getElementById("teams").innerHTML = ""
    document.getElementById("gameboard").innerHTML = ""
    document.getElementById("answer").innerHTML = ""
}

function displayTeams(teams) {
    teams.forEach(function (team, index, array) {
        createTeamCard(team.name, team.points);
    });
}

function createTeamCard(name, points) {
    var container = document.createElement("div")
    container.className = "mx-auto"

    var card = document.createElement("div");
    card.className = "card";

    var cardHead = document.createElement("div");
    cardHead.className = "card-header";
    cardHead.textContent = name

    var cardBody = document.createElement("div")
    cardBody.id = name + "-body"
    cardBody.className = "card-body text-center"

    var pointsElement = document.createElement("h5")
    pointsElement.className = "card-title"
    pointsElement.id = name + "-points"
    pointsElement.textContent = points

    card.append(cardHead)
    card.append(cardBody)
    cardBody.append(points)

    container.append(card)
    $("#teams").append(container)
}

function generateGameTable(categories) {
    console.log(categories)
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

function generateAnswer(answer) {
    console.log("generateAnswer" + answer)
    var answerContainer = document.getElementById("answer")
    answerContainer.className = "container"
    var container = document.createElement("div")
    container.className = "row text-center"
    answerContainer.append(container)

    if (isUrl(answer["answer"])) {
        var h1Answer = document.createElement("img")
        h1Answer.src = answer["answer"]
    } else {
        var h1Answer = document.createElement("h1")
        h1Answer.textContent = answer["answer"]
    }

    container.append(h1Answer)


    var container2 = document.createElement("div")
    container2.className = "row text-center"
    container2.id = "question"
    $(container2).hide()
    answerContainer.append(container2)


    var theQuestion = document.createElement("h1")
    theQuestion.textContent = answer["question"]

    container2.append(theQuestion)
}

function isUrl(s) {
    var regexp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/
    return regexp.test(s);
}

function revealQuestion() {
    console.log("trying to show")
    $("#question").show()
}

function displayBoard() {
    $('#answer').hide()
    $('#gameboard').show()
}

function displayAnswer() {
    $('#answer').show()
    $('#gameboard').hide()
}

// This is the entry point of the client.
initLoop();