// $(document).ready(function () {
//     $("input[name='field']").change(function () {
//         var maxAllowed = 3;
//         var cnt = $("input[name='field']:checked").length;
//         alert("Cucu'")
//         if (cnt > maxAllowed) {
//             $(this).prop("checked", "");
//             alert('You can select maximum ' + maxAllowed + ' technologies!!');
//         }
//     });
// });

counter = 0;
const max = 3;

function checkNumber(element){
    if(element.checked){
        if(counter < max)
            counter ++;
        else{
            element.checked = false;
            alert("You can choose only 3 specializations");
        }
    }else{
        counter --;
    }
    console.log(counter);
}