// http://www.simplecodestuffs.com/ajax-implementation-in-jsp-and-servlet-using-jquery/
$(document).ready(function() {

    $('#addButton').click(function(event) {
        $.ajax({
            type: "POST",
            url: "addTask.do",
            data: {
                taskDescription: $('#taskDescription').val()
            }
        })
        $("#table tr").remove();
        printAllTasks();
    });
});

https://netbeans.org/kb/docs/web/ajax-quickstart_ru.html
$(document).ready(printAllTasks());

function printAllTasks(){
    $.ajax({
        type: "POST",
        url: "allTasks.do",
        data: {
        },
        success: function(responseXML){
            var doc = new DOMParser().parseFromString(responseXML, 'text/xml');
            var tasks = doc.getElementsByTagName("task");
            for(var i = 0; i < tasks.length; i++) {
                var id = tasks[i].getElementsByTagName("id")[0].firstChild.data;
                var description = tasks[i].getElementsByTagName("description")[0].firstChild.data;
                var created = tasks[i].getElementsByTagName("created")[0].firstChild.data;
                var isDone = tasks[i].getElementsByTagName("isDone")[0].firstChild.data;
                $('#table').append("<tr><td>" + id + "</td><td>" + description + "</td><td>" + created + "</td><td>" + isDone + "</td></tr>");
            }
        }
    });
}

function printLastTask(){
    $.ajax({
        type: "POST",
        url: "lastTask.do",
        data: {
        },
        success: function(responseXML){
            var doc = new DOMParser().parseFromString(responseXML, 'text/xml');
            var tasks = doc.getElementsByTagName("task");
            for(var i = 0; i < tasks.length; i++) {
                var id = tasks[i].getElementsByTagName("id")[0].firstChild.data;
                var description = tasks[i].getElementsByTagName("description")[0].firstChild.data;
                var created = tasks[i].getElementsByTagName("created")[0].firstChild.data;
                var isDone = tasks[i].getElementsByTagName("isDone")[0].firstChild.data;
                $('#table').append("<tr><td>" + id + "</td><td>" + description + "</td><td>" + created + "</td><td>" + isDone + "</td></tr>");
            }
        }
    });
}
