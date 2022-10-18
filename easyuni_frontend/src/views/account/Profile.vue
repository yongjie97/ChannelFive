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
                        <h4 class="fw-normal mb-4 "><strong>My Profile</strong></h4>
                        <div class="form-outline mb-4">
                            <input type="email" id="email" name="email" v-model="email" class="form-control form-control-lg" disabled />
                            <label class="form-label">Email</label>
                        </div>          
                        <div class="form-outline mb-4">
                        <input type="text" id="displayName" name="displayName" v-model="displayName" class="form-control form-control-lg"  />
                        <label class="form-label" for="displayName">Display Name</label>
                        </div>  
                        <div class="form-outline mb-4">
                            <input type="text" id="zipCode" name="zipCode" v-model="zipCode" class="form-control form-control-lg"/>
                            <label class="form-label" for="zipCode">Zip Code</label>
                        </div>
                        <div class="form-outline mb-4">
                            <a class="text-dark" href="/profile/password">Change Password</a>
                        </div>
                        <div class="pt-3">
                            <input class="btn btn-warning text-white btn-lg btn-block" type="submit" value="Save Profile" />
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
        name: 'Profile',
        data() {
            return {
                email: '',
                displayName: '',
                zipCode: '',
            }
        },
        mounted() {
            axios.get('https://randomuser.me/api/') 
                .then(response => {
                    this.email = response.data.results[0].gender
                    this.displayName = response.data.results[0].email
                    this.zipCode = response.data.results[0].name.first
                })
        },
    }
</script>