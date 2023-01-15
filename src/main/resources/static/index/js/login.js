document.addEventListener('DOMContentLoaded',(e)=>{
    document.getElementById('open-login-form').addEventListener('click',
    (e)=>{
        document.getElementById('box').classList.add("boks_show");
    })

    document.getElementById('close-icon').addEventListener('click',
    (e)=>{
        document.getElementById('box').classList.remove("boks_show");
    })
})

