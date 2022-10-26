<template>
    <nav id="main_nav" class="navbar navbar-expand-lg navbar-light bg-white shadow">
        <div class="container d-flex justify-content-between align-items-center">
            <a class="navbar-brand h1" href="/">
                <span class="text-dark h4">Easy</span><span class="text-warning h4">Uni</span>
            </a>
            <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbar-toggler-success" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-md-between"
                id="navbar-toggler-success">
                <div class="mb-2 flex-fill justify-content-end">
                    <ul class="nav navbar-nav d-flex justify-content-end text-center text-dark">
                        <li class="nav-item">
                            <router-link class="nav-link px-3" to="/">Home</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link class="nav-link px-3" to="/course">Course</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link class="nav-link px-3" to="/discussion">Discussion</router-link>
                        </li>
                        <li class="nav-item" v-if="!loggedIn">
                            <router-link class="nav-link px-3" to="/login">Log In</router-link>
                        </li>
                        <li class="nav-item" v-if="loggedIn">
                            <router-link class="nav-link px-3" to="/profile">Profile</router-link>
                        </li>
                        <li class="nav-item" v-if="loggedIn">
                            <router-link class="nav-link px-3" to="#" @click="logOut()">Log Out</router-link>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</template>

<script>

import bs from "../assets/js/bootstrap.bundle.min.js"

export default {
    
    name: 'NavigationBar',
    data() {
        return {
            loggedIn: false
        }
    },
    mounted() {
        if (this.$cookies.get('token')) {
            this.loggedIn = true;
        }
    },
    methods: {
        logOut() {
            this.$cookies.remove('token');
            this.loggedIn = false;
            this.$router.push('/')
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