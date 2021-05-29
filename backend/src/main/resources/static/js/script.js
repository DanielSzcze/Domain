console.log("script");
console.log(window.location.href);
let username = window.location.href.toString();
let number = username.lastIndexOf("/") + 1;
username = username.substr(number);

getData(username);

function changeValue(value) {
    console.log(username);
    console.log(value);

    $.ajax({
        type : "POST",
        url : '/lolapp/changevalue',
        data : JSON.stringify({
            'id': value,
            'username' : username
        }),
        dataType : 'json',
        timeout : 100000,
        contentType:'application/json',
        success : function(data) {
            if (data === 1) {
                document.getElementById(value).style.backgroundColor = 'red';
            } else {
                document.getElementById(value).style.backgroundColor = 'green';
            }
            getData(username);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}

function getData(username) {
    $.ajax({
        type : "GET",
        url : '/lolapp/getdata/' + username,
        dataType: 'json',
        timeout : 100000,
        contentType: 'application/json',
        success : function (data) {
            data.forEach(initValues)
        }
    });

    function initValues(value, index, array){
        if (value === '1') {
            document.getElementById(index + 1).style.backgroundColor = 'red';
        }
    }
}

/*function changeStyle(data) {
    data.forEach(styles);

    function styles(value, index, array) {
        let ele = document.getElementById(value);
        console.log('ele: ' + ele);
        console.log('value: ' + value);
        console.log('array element: ' + array[index]);
        if (ele != null) {
            ele.style.backgroundColor = 'red';
        } /!*else if (array[index] === '0') {
            ele.style.backgroundColor = 'white';
        }*!/
    }
}*/
