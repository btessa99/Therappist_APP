function emailCheck(email){
    return email.match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
}

function formCheck(){
    let form = document.getElementById('form');
    form.addEventListener('submit', function(e){
        let email = document.getElementById('email');
        if(!emailCheck(email.value)){
            document.getElementById('error-field').textContent = 'Email format not correct'
            console.log('invalid email format')
            e.preventDefault();
            return;
        }
        let password = document.getElementById('password').value;
        let confirmation = document.getElementById('confirmation').value;
        if(!(password === confirmation)){
            console.log("password and confirmation don't match")
            document.getElementById('error-field').textContent = 'Password and confirmation don\'t match';
            e.preventDefault();
        }
    });
}