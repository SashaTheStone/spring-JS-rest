$(document).ready(function () {
    getAllUsers()
    getAllRoles()
})
let allUsers
let allRoles


async function getAllUsers() {
    let data = await fetch('http://localhost:8080/rest/users')
    allUsers = await data.json()
    allUserTable(allUsers)
    return allUsers
}

async function getAllRoles() {
    let data = await fetch('http://localhost:8080/rest/roles')
    allRoles = await data.json()
    return allRoles
}

function allUserTable(users) {
    // console.log("Запуск внутри таблицы")
    $('.myTbody').empty()

    $.each(users, function (i, user) {
        let role = user.roles
        let stringRole = ''

        for (let name in role) {
            stringRole += `${role[name].role?.replace("ROLE_","")}` + " "
        }

        $('<tr>').append(
            $('<td>').text(user.id),
            $('<td>').text(user.name),
            $('<td>').text(user.lastName),
            $('<td>').text(user.age),
            $('<td>').text(user.email),
            $('<td>').text(stringRole),
            $('<td>').append($('<button>').text("Edit").attr({
                "type": "button",
                "id": "buttonEdit",
                "class": "btn btn-info",
                "data-bs-toggle": "modal",
                "data-bs-target": "#editModal",
            }).data("user", user)),
            $('<td>').append($('<button>').text("Delete").attr({
                "type": "button",
                "id": "buttonDelete",
                "class": "btn btn-danger",
                "data-bs-toggle": "modal",
                "data-bs-target": "#deleteModal",
            }).data("user", user))
        ).appendTo('.myTbody')
    })
}

$(document).on('click', '#buttonEdit', function () {
    $('#editModal').modal('show')
    $('#selectRoles').empty()

    let user = $(this).data("user")
    $('#ID').val(user.id)
    $('#name').val(user.name)
    $('#lastName').val(user.lastName)
    $('#age').val(user.age)
    $('#email').val(user.email)
    $('#password').val(user.password)

    let sel = false

    $.each(allRoles, function (i, role) {
        // console.log(allRoles)
        $.each(user.roles, function (i, userRole) {
            if (userRole.role === role.role) {
                sel = true
            }

        })

        $('#selectRoles').append(
            $('<option>').text(role.role.replace("ROLE_","")).attr({
                "id" : role.id,
                "value" : role.role,
                "selected" : sel,
            })
        )
        sel = false
    })
})


$(document).on('click', '#submitEdit',async function () {
    let roles = []

    $('#selectRoles option:selected').each(function (index, value) {
        roles[index] = {
            id : value.id,
            role: value.value
        }
    })

    const user = {
        id: $('#ID').val(),
        name: $('#name').val(),
        lastName: $('#lastName').val(),
        age : $('#age').val(),
        email : $('#email').val(),
        password : $('#password').val(),
        roles : roles
    }

    try {
        const response = await fetch('http://localhost:8080/rest/change', {
            method : 'PATCH',
            body : JSON.stringify(user),
            headers: {
                'Content-Type' : 'application/json'
            }

        })

        $("#editClose").click();
        await getAllUsers()
        await getAllRoles()
        const json = await response.json()
        console.log('Success',JSON.stringify(json))

    }catch (error) {
        console.error('error',error)
    }
})


$(document).on('click', '#buttonDelete', function () {
    $('#deleteModal').modal('show')
    $('#selectRolesDel').empty()

    let user = $(this).data("user")
    $('#IDDel').val(user.id)
    $('#nameDel').val(user.name)
    $('#lastnameDel').val(user.lastName)
    $('#ageDel').val(user.age)
    $('#emailDel').val(user.email)
    $('#passwordDel').val(user.password)

    let sel = false

    $.each(allRoles, function (i, role) {
        $.each(user.roles, function (i, userRole) {
            if (userRole.role === role.role) {
                sel = true
            }
        })

        $('#selectRolesDel').append(
            $('<option>').text(role.role.replace("ROLE_","")).attr({
                "id" : role.id,
                "value" : role.role,
                "selected" : sel,
            })
        )
        sel = false
    })
})


$(document).on('click', '#submitDelete',async function () {

    try {
        const response = await fetch('http://localhost:8080/rest/delete/' + $('#IDDel').val(), {
            method : 'DELETE',
        })

        $("#closeDeleteModal").click();
        await getAllUsers()
        await getAllRoles()
        const json = await response.json()
        console.log('Success',JSON.stringify(json))

    }catch (error) {
        console.error('error',error)
    }
})



