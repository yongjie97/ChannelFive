<template>
    {{unidata}}
</template>

<script>
    export default {
        name: 'University',
        data() {
            return {
                unidata: null
            }
        },
        mounted() {
            axios({method:'get', 
                    url: 'http://localhost:8080/university/' + this.$route.params.uniName
            })
            .then(response => {
                if (response != null) {
                    this.unidata = response.data
                }
            }).catch((error) => {
                alert(error.message)
            })
        },
        computed: {
            axiosParams() {
                const params = new URLSearchParams();
                params.append('', 'national-university-of-singapore');
                return params;
            }
        }
    }
</script>
