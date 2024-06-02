islogin = JSON.parse(localStorage.getItem('islogin')) || false
isvip = JSON.parse(localStorage.getItem('isvip')) || false
const head = document.querySelector('.user-head')
console.log(1)

logout = function () {
    const head = document.querySelector('.user-head')
    islogin = false
    localStorage.setItem('islogin', JSON.stringify(islogin))
    document.querySelectorAll('.btn.btn-primary')[1].style.display = 'inline-block'
    head.style.display = 'none'
}

buy = function () {
    isvip = true
    localStorage.setItem('isvip', JSON.stringify(true))
    location.reload()
}

function checkMemberStatus(event) {
    if (!isvip) {
        event.preventDefault(); // 阻止默认行为（跳转）
        alert('您需要成为会员才能观看此电影。');
    }
    if(!islogin) {
        event.preventDefault();
        alert('请先登录后再观影')
    }
}

window.onload = () => {
    const head = document.querySelector('.user-head')
    if(islogin){
        document.querySelectorAll('.btn.btn-primary')[1].style.display = 'none'
        head.style.display = 'inline-block'
        console.log(isvip)
        if(isvip){
            head.classList.add('vip')
        } else {
            head.classList.remove('vip')
        }
        console.log(head.classList)
    } else {
        document.querySelectorAll('.btn.btn-primary')[1].style.display = 'inline-block'
        head.style.display = 'none'
    }
}


document.addEventListener('DOMContentLoaded', function () {
    // 登录表单提交事件
    document.querySelector('#Login form').addEventListener('submit', function (event) {
        event.preventDefault();
        const email = document.getElementById('email1').value;
        const password = document.getElementById('exampleInputPassword1').value;

        const storedEmail = localStorage.getItem('email');
        const storedPassword = localStorage.getItem('password');

        if (email === storedEmail && password === storedPassword) {
            alert('登录成功');
            $('#myModal').modal('hide');
            const head = document.querySelector('.user-head')
            document.querySelectorAll('.btn.btn-primary')[1].style.display = 'none'
            islogin = true
            localStorage.setItem('islogin', JSON.stringify(true))
            console.log(localStorage.getItem('islogin'))
            if(isvip){
                head.classList.add('vip')
            } else {
                head.classList.remove('vip')
            }
            head.style.display = 'inline-block'
        } else {
            alert('电子邮件或密码错误');
        }
    });

    // 注册表单提交事件
    const savebtn = document.querySelector('.save')
    savebtn.addEventListener('click', function (event) {

        var emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

        const name = document.querySelector('#re-name').value;
        const email = document.querySelector('#email2').value;
        const mobile = document.querySelector('#mobile').value;
        const password = document.querySelector('#password').value;

        if(name === '' || (email === '' || !emailRegex.test(email)) || mobile === '' || password === '') {
            alert('请输入有效值')
        } else {
            localStorage.setItem('name', name);
            localStorage.setItem('email', email);
            localStorage.setItem('mobile', mobile);
            localStorage.setItem('password', password);
            alert('注册成功');
            location.reload()
        }

    });
});
    