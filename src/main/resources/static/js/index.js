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