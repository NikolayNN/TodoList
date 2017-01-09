// http://www.simplecodestuffs.com/ajax-implementation-in-jsp-and-servlet-using-jquery/
$(document).ready(function() {
    $('#addButton').click(function(event) {
        var description = $('#taskDescription').val();
        $.get('addTask.do', {
            taskDescription : description
        });
    });
});

// https://netbeans.org/kb/docs/web/ajax-quickstart_ru.html
$(document).ready(function(){
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
                alert(id + " " + description + " " + created + " " + isDone);
                $('#table').append("<tr><td>" + id + "</td><td>" + description + "</td><td>" + created + "</td><td>" + isDone + "</td></tr>");
            }
        }
    });
});
