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
            <li class="breadcrumb-item active" aria-current="page">Change Photo</li>
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

          <h5 class="card-header text-center">Change Photo</h5>

          <div class="card-body">
            <h5 class="card-title text-center">${userModel.firstName} ${userModel.lastName}</h5>
            <h6 class="card-subtitle mb-2 text-center text-muted">Please provide a new image to change</h6>
            <h6 class="card-subtitle mb-2 text-center text-muted">
                <b style="color:red;" >NOTE: Re-start Spring application after uploading the image as resources are bundled all together by the IDE before running the system.<br>
                Otherwise, please navigate to /resources/static/photos and use some sample images I have included.
                Although this is an IDE problem, in a real-world scenario, these images would be saved somewhere else too.
                </b>
            </h6>

            <form id="change-photo-user" action="/user/profile/changephoto" name="userForm" method="post" enctype="multipart/form-data">

              <input type="hidden" name="id" value="${userForm.id}">

              <div class="mb-3 text-center">
                <input type="file" id="imageFile" name="imageFile">
              </div>

              <div class="mb-3 text-center">
                <button type="submit" class="btn btn-primary align-self-center">Update Photo</button>
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

