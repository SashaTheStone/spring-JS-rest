$(document).ready(function () {
    getUser()
})

async function getUser() {
    let user = await fetch('/rest/user')
        .then(res => res.json())
    // console.log(user)
navBar(user)
table(user)
}

function navBar(user) {
    let role = user.roles
    let stringRole = ''

    for (let name in role) {
        stringRole += `${role[name].role?.replace("ROLE_","")}` + " "
    }

    $('#NameNavBar').append(user.email)
    $('#RoleNavBar').append(stringRole)
}

function table (user){
    let role = user.roles
    let stringRole = ''

    for (let name in role) {
        stringRole += `${role[name].role?.replace("ROLE_","")}` + " "
    }

$('#tdUserId').append(user.id)
$('#tdUserName').append(user.name)
$('#tdUserLastName').append(user.lastName)
$('#tdUserAge').append(user.age)
$('#tdUserEmail').append(user.email)
$('#tdUserRole').append(stringRole)
}