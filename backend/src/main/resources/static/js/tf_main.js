let username = window.location.href.toString();
let number = username.lastIndexOf("/") + 1;
username = username.substr(number);

$.ajax({
type : "GET",
url : '/tf/getIncomeTypes',
dataType: 'json',
timeout : 100000,
contentType: 'application/json',
    success : function (data) {
        let first = true;
        for (let i in data) {
            for (let j in data[i]) {
                document.getElementById("type")
                    .insertAdjacentHTML("beforeend",
                        "<option value=" + data[i][j] + ">" + data[i][j] + "</option>")
            }
            if (first) {
                document.getElementById("type")
                    .insertAdjacentHTML("beforeend",
                        "<option></option>")
                first = false;
            }
        }
    }
});
