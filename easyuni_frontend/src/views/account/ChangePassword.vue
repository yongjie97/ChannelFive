<template>
            <section class="vh-100" style="background-color: #E0E0E0;">
            <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center h-100">
                <div class="col col-xl-7">
                <div class="card">
                    <div class="row">
                    <div class="d-flex">
                        <div class="card-body p-4 p-lg-5 text-black">
                        <form id="profileForm" action="/profile/password" method="post">    
                            <h4 class="fw-normal mb-4 "><strong>Change Password</strong></h4>        
                            <div class="form-outline mb-4">
                            <input type="text" id="oldPassword" name="oldPassword" class="form-control form-control-lg" />
                            <label class="form-label" for="oldPassword">Old Password</label>
                            </div>  
                            <div class="form-outline mb-4">
                                <input type="text" id="newPassword" name="newPassword" class="form-control form-control-lg" />
                                <label class="form-label" for="newPassword">New Password</label>
                            </div>
                            <div class="pt-3">
                                <input class="btn btn-warning text-white btn-lg btn-block" type="submit" value="Change Password" />
                            </div>
                        </form>
        
                        </div>
                    </div>
                    </div>
                </div>
                </div>
            </div>
            </div>
        </section>
</template>
      
<script>    
    export default {
        name: 'ChangePassword'
    }
</script>
