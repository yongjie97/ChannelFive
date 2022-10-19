<template>
        <nav id="main_nav" class="navbar navbar-expand-lg navbar-light bg-white shadow">
            <div class="container d-flex justify-content-between align-items-center">
                <a class="navbar-brand h1" href="/">
                    <span class="text-dark h4">Easy</span><span class="text-warning h4">Uni</span>
                </a>
                <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-toggler-success" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="align-self-center collapse navbar-collapse flex-fill  d-lg-flex justify-content-lg-between" id="navbar-toggler-success">
                    <div class="flex-fill mx-xl-5 mb-2">
                        <ul class="nav navbar-nav d-flex justify-content-end mx-xl-1 text-center text-dark">
                            <li class="nav-item">
                                <a class="nav-link px-3" href="/">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link px-3" href="#">Course</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link px-3" href="work.html">Discussion</a>
                            </li>
                            <li class="nav-item" v-if="!loggedIn">
                                <a class="nav-link px-3" href="/login">Log In</a>
                            </li>
                            <li class="nav-item" v-if="loggedIn">
                                <a class="nav-link px-3" href="/profile">Profile</a>
                            </li>
                            <li class="nav-item" v-if="loggedIn">
                                <a class="nav-link px-3" @click="logOut()" href="http://localhost:3000">Log Out</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
</template>

<script>
    export default {
        name: 'NavigationBar',
        data() {
            return { 
                loggedIn: false
            }
        },
        created() {
            if (this.$cookies.get('token')) {
                this.loggedIn = true;
            }
        },
        methods: {
            logOut() {
                this.$cookies.remove('token');
            },
        }
    }
</script>

<style scoped>
    .nav-link:hover {
        color: #f0ad4e;
    }

    .nav-link:active {
        color: #f6cb90; 
    }

</style>