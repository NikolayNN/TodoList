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
                success: function () {
                    clearTable();
                    printAllTasks();
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
    startLoadingAnimation();
    $.ajax({
        type: "POST",
        url: "allTasks.do",
        data: {},
        success: function (responseXML) {
           drawTable(responseXML);
           stopLoadingAnimation();
        }
    });
}

function startLoadingAnimation(){
    var imgObj = $("#loadImg");
    imgObj.show();
}

function stopLoadingAnimation(){
    $("#loadImg").hide();
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
$(document).ready(function () {
    var id;
    $('#table').on('change','.checkboxIsDone', function(){
        id = $(this).attr("id");
        changeTaskStatus(id);
    });

});

function changeTaskStatus(id){
    $.ajax({
        type: "POST",
        url: "changeStatus.do",
        data: {
            taskId: id
        },
        success: function () {
            clearTable();
            printAllTasks();
        }
    })
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
    $('#table .table-data').remove();
}

function drawTable(xml) {
    var doc = new DOMParser().parseFromString(xml, 'text/xml');
    var tasks = doc.getElementsByTagName("task");
    for (var i = 0; i < tasks.length; i++) {
        var id = tasks[i].getElementsByTagName("id")[0].firstChild.data;
        var description = tasks[i].getElementsByTagName("description")[0].firstChild.data;
        var created = tasks[i].getElementsByTagName("createdDate")[0].firstChild.data;
        var isDone = tasks[i].getElementsByTagName("isDone")[0].firstChild.data;
        $('#table').append(
            "<tr class='table-data'>" +
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




