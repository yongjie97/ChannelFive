<template>
    <div class="banner-wrapper bg-light">
        <div id="index_banner" class="banner-vertical-center-index container-fluid pt-5">

            <!-- Start slider -->
            <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"></li>
                    <!--<li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"></li>-->
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">

                        <div class="py-5 row d-flex align-items-center">
                            <div class="banner-content col-lg-8 col-8 offset-2 m-lg-auto text-left py-5 pb-5">
                                <h1
                                    class="banner-heading h1 text-warning display-3 mb-0 pb-5 mx-0 px-0 light-300 typo-space-line">
                                    <strong>Welcome to EasyUni!</strong>
                                </h1>

                                <p class="banner-body text-light py-3 mx-0 px-0">View all the latest median salary and
                                    indicative grade profile across all
                                    <strong>universities</strong> in Singapore. Feeling unsure about your university
                                    prospect? Use our discussion board features to clarify your doubts.
                                </p>
                                <router-link to="/course" class="banner-button btn rounded-pill btn-outline-warning btn-lg px-4" href=""
                                    role="button">Browse Courses</router-link>
                            </div>
                        </div>

                    </div>
                    <!--<div class="carousel-item">

                            <div class="py-5 row d-flex align-items-center">
                                <div class="banner-content col-lg-8 col-8 offset-2 m-lg-auto text-left py-5 pb-5">
                                    <h1 class="banner-heading h1 text-secondary display-3 mb-0 pb-3 mx-0 px-0 light-300">
                                        HTML CSS Template with Bootstrap 5 Beta 1
                                    </h1>
                                    <p class="banner-body text-muted py-3">
                                        You are not allowed to re-distribute this Purple Buzz HTML template as a downloadable ZIP file on any kind of Free CSS collection websites. This is strongly prohibited. Please contact TemplateMo for more information.
                                    </p>
                                    <a class="banner-button btn rounded-pill btn-outline-primary btn-lg px-4" href="#" role="button">Get Started</a>
                                </div>
                            </div>

                        </div>-->
                </div>
            </div>
            <!-- End slider -->

        </div>
    </div>
    <!-- End Banner Hero -->
</template>

<script>
export default {
    name: 'CarouselSlide'
};
</script>

<style scoped src="../assets/style/templatemo.css"></style>