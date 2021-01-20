<!doctype html>
<html lang="en">

  <head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Portal</title>
    <link rel="stylesheet" href="/css/colors.css">
    <script src='https://www.google.com/recaptcha/api.js'></script>
  </head>

  <body class="mainBackground">

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">Portal</li>
            <li class="breadcrumb-item active" aria-current="page">Register</li>
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

        <div class="card" style="margin-top:3%;">

          <h5 class="card-header text-center">Dataverse Register Portal</h5>

          <div class="card-body">
            <h5 class="card-title text-center">Register</h5>
            <h6 class="card-subtitle mb-2 text-center text-muted">Please fill in this form to create an account.</h6>

            <form id="create-user" action="/register" name="userForm" method="post">

              <div class="mb-3">
                <label for="firstName" class="form-label">First Name</label>
                <@spring.bind "userForm.firstName"/>
                <input type="text" id="firstName" name="firstName" class="form-control" placeholder="First Name" required autofocus>
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
                <@spring.bind "userForm.lastName"/>
                <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Last Name" required >
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
                <@spring.bind "userForm.email"/>
                <input type="email" id="email" name="email" class="form-control" placeholder="Email address" required >
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
                <@spring.bind "userForm.phone"/>
                <input type="text" id="phone" name="phone" class="form-control" placeholder="Phone">
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
                <@spring.bind "userForm.company"/>
                <input type="text" id="company" name="company" class="form-control" placeholder="Company Name">
                <#if spring.status.error>
                    <ul>
                        <#list spring.status.errorMessages as error>
                            <li style="color: #dc3545">${error}</li>
                        </#list>
                    </ul>
                </#if>
              </div>

              <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <@spring.bind "userForm.password"/>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password">
                <#if spring.status.error>
                    <ul>
                        <#list spring.status.errorMessages as error>
                            <li style="color: #dc3545">${error}</li>
                        </#list>
                    </ul>
                </#if>
              </div>

              <div class="g-recaptcha"
                data-sitekey="6Ld-rTMaAAAAAMWDAktoyYIKZAejtdWnGSNSHf_e">
              </div>
            <span id="captchaError" class="alert alert-danger col-sm-4"
              style="display:none"></span>

              <div class="mb-3 text-center">
                <button type="submit" class="btn btn-primary align-self-center">Register</button>
              </div>

            </form>

          </div>
        </div>
    </div>

  </body>
</html>

