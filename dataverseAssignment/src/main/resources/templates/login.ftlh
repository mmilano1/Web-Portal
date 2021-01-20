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
            <li class="breadcrumb-item active" aria-current="page">Portal</li>
        </ol>
    </nav>
    <div class="container">

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

          <h5 class="card-header text-center">Dataverse Portal</h5>

          <div class="card-body">
            <h5 class="card-title text-center">Log in</h5>
            <h6 class="card-subtitle mb-2 text-center text-muted">Log in to access your information.</h6>

            <form action="/login" method="post">
              <div class="mb-3">
                <label for="email" class="form-label">Email address</label>
                <input type="email" id="email" name="email" class="form-control" placeholder="Email address" required autofocus>
              </div>
              <div class="mb-3">
                <label for="password"  class="form-label">Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
              </div>
              <#if RequestParameters.error??>
                  <div class="alert alert-danger align-self-center" role="alert">
                      Not valid credentials!
                  </div>
               </#if>
              <div class="mb-3 text-center">
                <button type="submit" class="btn btn-primary align-self-center">Log In</button>
              </div>
            </form>
             <hr/>
              <div class="mb-3 text-center">
                 <a href="/register" class="btn btn-primary align-self-center">Register</a>
             </div>
          </div>
        </div>
    </div>

  </body>
</html>

