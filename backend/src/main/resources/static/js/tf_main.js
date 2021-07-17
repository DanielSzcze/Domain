let username = window.location.href.toString();
let number = username.lastIndexOf("/") + 1;
username = username.substr(number);

let typeValues;

$.ajax({
    type : "GET",
    url : '/tf/getIncomeTypes',
    dataType: 'json',
    timeout : 100000,
    contentType: 'application/json',
    success : function (data) {
        typeValues = data;
        let first = true;
        for (let i in data) {
            let firstJ = true;
            for (let j in data[i]) {
                if (firstJ) {
                    firstJ = false;
                } else {
                    document.getElementById("typeSe")
                        .insertAdjacentHTML("beforeend",
                            "<option value=" + data[i][j] + ">" + data[i][j] + "</option>")
                }
            }
            if (first) {
                document.getElementById("typeSe")
                    .insertAdjacentHTML("beforeend",
                        "<option></option>")
                first = false;
            }
        }
    }
});

$.ajax({
    type: "GET",
    url: '/tf/getPeriodTypes',
    dataType: 'json',
    timeout: 100000,
    contentType: 'application/json',
    success: function (data) {
        let first = true;
        for (let i in data) {
            if (first) {
                first = false;
            } else {
                document.getElementById("time")
                    .insertAdjacentHTML("beforeend",
                        "<option value=" + data[i] + ">" + data[i] + "</option>")
            }
        }
    }
})

function createRecord(type, amount, time) {
    console.log(username + ", " + type + ", " + amount + ", " + time)
    $.ajax({
        type: "POST",
        url: '/tf/createRecord',
        data: JSON.stringify({
            "username" : username,
            "type" : type,
            "amount" : amount,
            "time" : time
        }),
        dataType : 'json',
        timeout : 100000,
        contentType:'application/json',
        success(url) {
            if (url === 1) {
                update();
            }
        }
    })
}

function update() {
    $.ajax({
        type: "GET",
        url: '/tf/' + username + '/getRecords',
        dataType: 'json',
        timeout: 100000,
        contentType: 'application/json',
        success: function (data) {
            updateTable(data);
        }
    });
}

function updateTable(data) {
    if (document.getElementById("table-script") !== null) {
        let remove = document.getElementById("table-script").childNodes
        if (remove.length > 0) {
            while (remove.length >0){
                remove.forEach(value => value.remove());
            }
        }

        let parent = document.getElementById("table-script");

        for (let i in data) {
            let idData = data[i].id;
            let time = data[i].period;
            let amount = data[i].amount;
            let charge = data[i].charge;
            let income = data[i].income;

            let tr = document.createElement("tr");
            tr.setAttribute("id", "tr" + i)
            parent.appendChild(tr);

            let td1 = document.createElement("td");
            td1.setAttribute("id", "td1" + i);
            td1.setAttribute("class", "table-id");
            td1.innerText = idData;
            tr.appendChild(td1);

            let td2 = document.createElement("td");
            td2.setAttribute("id", "td2" + i);
            td2.setAttribute("class", "table-time");
            td2.innerText = time;
            tr.appendChild(td2);

            let td3 = document.createElement("td");
            td3.setAttribute("id", "td3" + i);
            if (charge === "null") {
                td3.setAttribute("class", "table-amount-I");
            } else {
                td3.setAttribute("class", "table-amount-C");
            }
            td3.innerText = amount;
            tr.appendChild(td3);

            let td4 = document.createElement("td");
            td4.setAttribute("id", "td4" + i);
            td4.setAttribute("class", "table-income");
            if (income !== "null") {
                td4.innerText = income;
            } else {
                td4.innerText = "";
            }
            tr.appendChild(td4);

            let td5 = document.createElement("td");
            td5.setAttribute("id", "td4" + i);
            td5.setAttribute("class", "table-charge");
            if (charge !== "null") {
                td5.innerText = charge;
            } else {
                td5.innerText = "";
            }
            tr.appendChild(td5);
        }
    } else {
        let value = document.createElement("table");
        value.setAttribute("id", "table-script");
        value.setAttribute("class", "table-script");
        document.body.insertAdjacentElement("beforeend", value);
        updateTable(data);
    }
}
