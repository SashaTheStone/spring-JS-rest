$(document).ready(function () {
    getUser()
    getAllRoles()
})

async function getUser() {
    let user = await fetch('http://localhost:8080/rest/user')
        .then(res => res.json())
    // console.log(user)
    navBar(user)
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

async function getAllRoles() {
    let data = await fetch('http://localhost:8080/rest/roles')
    allRoles = await data.json()
    // console.log(allRoles)

    $.each(allRoles, function (i, role) {
        $('#selectRoles').append(
            $('<option>').text(role.role.replace("ROLE_","")).attr({
                "id": role.id,
                "value": role
            })
        )
    })
    return allRoles
}

$(document).on('click','#createButton', async function () {
    let roles = []
    $('#selectRoles option:selected').each(function (index, value) {
        roles[index] = {
            id : value.id,
            role : value.value
        }
    })

    const user = {
        name : $('#newFirstName').val(),
        lastName: $('#newSecondName').val(),
        age: $('#newAge').val(),
        email : $('#newEmail').val(),
        password: $('#newPassword').val(),
        roles : roles
    }

    try {
        const response = await fetch('http://localhost:8080/rest/save',{
            method : 'POST',
            body : JSON.stringify(user),
            headers : {
                'Content-Type' : 'application/json'
            }
        })
        // $('#allUserPage').click()
        const json = await response.json()
        console.log('success',JSON.stringify(json))
        // alert("Осуществлена попытка создания пользователя")
    }catch (error) {
        console.error('error',error)
    }
})