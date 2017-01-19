$(document).ready(printAllTasks());

$(document).ready(function () {
    $('#addButton').click(function (event) {
        var taskDescription = $('#taskDescription').val();
        $('#addTask')[0].reset();
        if (taskDescription.length != 0) {
            $.ajax({
                type: "POST",
                url: "addTask.do",
                data: {
                    taskDescription: taskDescription
                },
                success: function (responseXML) {
                    clearTable();
                    drawTable(xml);
                }
            })
        } else {
            alert("input task description!");
        }
    });
});

$(document).ready(function () {
    $('#checkboxNotCompletedTasks').change(function () {
        checkboxShowNotCompletedTasks();
    });
});

function printAllTasks() {
    $.ajax({
        type: "POST",
        url: "allTasks.do",
        data: {},
        success: function (responseXML) {
           drawTable(responseXML);
        }
    });
}

function checkboxShowNotCompletedTasks() {
    if ($('#checkboxNotCompletedTasks').is(':checked')) {
        clearTable();
        showNotCompletedTasks();
    }
    else {
        clearTable();
        printAllTasks();
    }
}

function showNotCompletedTasks() {
    $.ajax({
        type: "POST",
        url: "notCompletedTasks.do",
        success: function (responseXML) {
            clearTable();
            drawTable(responseXML);
        }
    })
}

function clearTable() {
    $('#table tr').remove();
}

function drawTable(xml) {
    var doc = new DOMParser().parseFromString(xml, 'text/xml');
    var tasks = doc.getElementsByTagName("task");
    for (var i = 0; i < tasks.length; i++) {
        var id = tasks[i].getElementsByTagName("id")[0].firstChild.data;
        var description = tasks[i].getElementsByTagName("description")[0].firstChild.data;
        var created = tasks[i].getElementsByTagName("created")[0].firstChild.data;
        var isDone = tasks[i].getElementsByTagName("isDone")[0].firstChild.data;
        var checked;
        $('#table').append(
            "<tr>" +
                "<td><input class=\"checkboxIsDone\" type=\"checkbox\" id=\"" + id + "\" " + isChecked(isDone) + "></td>" +
                "<td>" + id + "</td>" +
                "<td>" + description + "</td>" +
                "<td>" + created + "</td>" +
                "<td>" + isDone + "</td>" +
            "</tr>");
    }
}

function isChecked(isDone) {
    var checked;
    if(isDone.localeCompare("false")){
        checked = "checked";
    }else{
        checked = "";
    }
    return checked;
}




