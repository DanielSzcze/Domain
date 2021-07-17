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
            for (let j in data[i]) {
                document.getElementById("typeSe")
                    .insertAdjacentHTML("beforeend",
                        "<option value=" + data[i][j] + ">" + data[i][j] + "</option>")
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
        for (let i in data) {
            document.getElementById("time")
                .insertAdjacentHTML("beforeend",
                    "<option value="  + data[i] + ">" + data[i] + "</option>")
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
                location.reload();
            }
        }
    })
}

