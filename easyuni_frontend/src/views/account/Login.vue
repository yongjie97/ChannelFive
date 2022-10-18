<template>
    <section class="vh-100" style="background-color: #E0E0E0;">
            <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col col-xl-10">
                <div class="card">
                    <div class="row g-0">
                        <div class="col-md-6 col-lg-5 d-flex align-items-center bg-custom">
                            <div class="text-white py-4 p-md-5 mx-md-4 mx-4">
                            <h4 class="mb-4 ">Login to join discussions</h4>
                            <p class="small mb-0">Gain access to our popular discussion boards with countless topics to begin your university journey.</p>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-7 d-flex align-items-center">
                        <div class="card-body p-4 p-lg-5 text-black">
        
                        <form id="loginForm" action="http://localhost:8080/login" method="post">
                            <h4 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Sign into your account</h4>
        
                            <div class="form-outline mb-4">
                            <input type="email" id="email" name="email" class="form-control form-control-lg" />
                            <label class="form-label" for="email">Email address</label>
                            </div>
        
                            <div class="form-outline mb-4">
                            <input type="password" id="password" name="password" class="form-control form-control-lg" />
                            <label class="form-label" for="password">Password</label>
                            </div>
        
                            <div class="pt-1 mb-4">
                            <input class="btn btn-warning btn-lg btn-block text-white" type="submit" value="Login"/>
                            </div>
        
                            <a class="small text-muted" href="/forgot_password">Forgot password?</a>
                            <p class="mb-5 pb-lg-2 text-dark">Don't have an account? <a class="text-dark" href="/register">Register here</a></p>
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
        name: 'Login'
    }
</script>

<style scoped>
    .bg-custom {
        background-image: url('../../assets/image/login.jpg')
    }

</style>