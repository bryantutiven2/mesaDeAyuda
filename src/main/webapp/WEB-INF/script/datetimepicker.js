jQuery.datetimepicker.setDateFormatter('moment');
$("#dtPicker").datetimepicker({
    timepicker: true,
    datepicker: true,
    format: 'DD/MM/YYYY HH:mm',
    hours12: false
});
$('#toggle').on('click', function(){
    $('#dtPicker').datetimepicker('toggle');
});