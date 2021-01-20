<!doctype html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <title>Portal</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/css/colors.css">
    <link rel="stylesheet" href="/css/main.css">
  </head>

  <body class="mainBackground">

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">Portal</li>
            <li class="breadcrumb-item active" aria-current="page">Profile</li>
        </ol>
    </nav>

    <#import "/spring.ftl" as spring />

    <div class="container" style="margin-bottom:5%;">

        <#if redirectMessageError??>
            <div class="card text-white bg-danger">
                <div class="card-body">
                    ${redirectMessageError!""}
                </div>
            </div>
        </#if>

        <#if redirectMessageSuccess??>
            <div class="card text-white bg-success">
                <div class="card-body">
                    ${redirectMessageSuccess!""}
                </div>
            </div>
        </#if>

        <div class="card" style="margin-top:3%;">

          <h5 class="card-header text-center">Profile</h5>

          <div class="card-body">

          <#if userModel.photoPath??>
            <#if userModel.photoPath == "">
              <div class="text-center" ><img src="/photos/placeholder.png" class="photo"></div>
            <#else>
               <div class="text-center" ><img src=${userModel.photoPath} class="photo"></div>
            </#if>
          <#else>
              <div class="text-center" ><img src="/photos/placeholder.png" class="photo"></div>
          </#if>

            <h5 class="card-title text-center">${userModel.firstName} ${userModel.lastName}</h5>
            <h6 class="card-subtitle mb-2 text-center text-muted">Welcome, here are your details</h6>

            <form id="edit-user" action="/user/edit" method="post">

              <input type="hidden" name="id" value="${userModel.id}">

              <div class="mb-3">
                <label for="firstName" class="form-label">First Name</label>
                <@spring.bind "userModel.firstName"/>
                <input type="text" pattern="[a-zA-Z]*" id="firstName" name="firstName" class="form-control" placeholder="First Name" value="${userModel.firstName}" required autofocus>
                <#if spring.status.error>
                    <ul>
                        <#list spring.status.errorMessages as error>
                            <li style="color: #dc3545">${error}</li>
                        </#list>
                    </ul>
                </#if>
              </div>

              <div class="mb-3">
                <label for="lastName" class="form-label">Last name</label>
                <@spring.bind "userModel.lastName"/>
                <input type="text" pattern="[a-zA-Z]*" id="lastName" name="lastName" class="form-control" placeholder="Last Name" value="${userModel.lastName}" required >
                <#if spring.status.error>
                    <ul>
                        <#list spring.status.errorMessages as error>
                            <li style="color: #dc3545">${error}</li>
                        </#list>
                    </ul>
                </#if>
              </div>

              <div class="mb-3">
                <label for="email" class="form-label">Email address</label>
                <@spring.bind "userModel.email"/>
                <input type="email" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,60}" id="email" name="email" class="form-control" placeholder="Email address" value="${userModel.email}" required >
                <#if spring.status.error>
                    <ul>
                        <#list spring.status.errorMessages as error>
                            <li style="color: #dc3545">${error}</li>
                        </#list>
                    </ul>
                </#if>
              </div>

              <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <@spring.bind "userModel.phone"/>
                <input type="text" pattern="((\+)\d{2}|(00)\d{2})?\d{10}" id="phone" name="phone" class="form-control" placeholder="Phone" value="${userModel.phone}">
                <#if spring.status.error>
                    <ul>
                        <#list spring.status.errorMessages as error>
                            <li style="color: #dc3545">${error}</li>
                        </#list>
                    </ul>
                </#if>
              </div>

              <div class="mb-3">
                <label for="company" class="form-label">Company</label>
                <@spring.bind "userModel.company"/>
                <input type="text" pattern="[a-zA-Z]*" id="company" name="company" class="form-control" placeholder="Company Name" value="${userModel.company}">
                <#if spring.status.error>
                    <ul>
                        <#list spring.status.errorMessages as error>
                            <li style="color: #dc3545">${error}</li>
                        </#list>
                    </ul>
                </#if>
              </div>

              <div class="mb-3 text-center">
                <button type="submit" class="btn btn-primary align-self-center">Update Details</button>
              </div>
            <hr/>

              <div class="mb-3 text-center">
                <a href="/user/profile/changepassword" class="btn btn-secondary align-self-center">Change Password</a>
                <a href="/user/profile/changephoto" class="btn btn-secondary align-self-center">Change Photo</a>
              </div>

            </form>
            <hr/>
            <form action="/logout">
              <div class="mb-3 text-center">
                 <button class="btn btn-danger my-2 my-sm-0 text-uppercase" type="submit">Logout</button>
              </div>
            </form>

          </div>
        </div>
    </div>

  </body>
</html>