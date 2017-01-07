// http://www.simplecodestuffs.com/ajax-implementation-in-jsp-and-servlet-using-jquery/
$(document).ready(function() {
    $('#addButton').click(function(event) {
        var description = $('#taskDescription').val();
        $.get('addTask.do', {
            taskDescription : description
        });
    });
});