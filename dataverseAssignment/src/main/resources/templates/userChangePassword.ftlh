<!doctype html>
<html lang="en">

  <head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Portal</title>
    <link rel="stylesheet" href="/css/colors.css">
  </head>

  <body class="mainBackground">

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">Portal</li>
            <li class="breadcrumb-item active" aria-current="page">Profile</li>
            <li class="breadcrumb-item active" aria-current="page">Change Password</li>
        </ol>
    </nav>

    <#import "/spring.ftl" as spring />

    <div class="container" style="margin-bottom:5%;">

        <div class="card" style="margin-top:3%;">

          <h5 class="card-header text-center">Change Password</h5>

          <div class="card-body">
            <h5 class="card-title text-center">${userModel.firstName} ${userModel.lastName}</h5>
            <h6 class="card-subtitle mb-2 text-center text-muted">Please input new password to change</h6>

            <form id="change-password-user" action="/user/profile/changepassword" name="userForm" method="post">

              <input type="hidden" name="id" value="${userForm.id}">

              <div class="mb-3">
                <label for="company" class="form-label">Password</label>
                <@spring.bind "userForm.password"/>
                <input type="password" pattern="(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}" id="password" name="password" aria-describedby="passwordHelp" class="form-control">
                <#if spring.status.error>
                    <ul>
                        <#list spring.status.errorMessages as error>
                            <li style="color: #dc3545">${error}</li>
                        </#list>
                    </ul>
                </#if>
              </div>

              <div class="mb-3 text-center">
                <button type="submit" class="btn btn-primary align-self-center">Update Password</button>
              </div>
              <hr>
              <div class="mb-3 text-center">
                <a href = "/user/profile/" class="btn btn-primary align-self-center">Back</a>
              </div>

            </form>

          </div>
        </div>
    </div>

  </body>
</html>

