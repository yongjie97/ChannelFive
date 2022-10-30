<template>
  <div class="min-vh-100">
    <div class="container py-5">
      <div class="row d-flex justify-content-center justify-content-start-s align-items-center">
        <div class="col col-xl-10">
          <div class="row my-5 mb-3 justify-content-between justify-content-between-l">
            <div class="col col-auto mb-3">
              <h3>{{ courseData.degree }}</h3>
              <p class="mb-1 text-muted">Grades: <strong>{{ grades }}</strong></p>
              <p class="mb-1 text-muted">Rank Point: <strong>{{ rp }}</strong></p>
              <p class="mb-1 text-muted">GPA: <strong>{{ gpa }}</strong></p>
            </div>
            <div class="col col-12 col-lg-auto">
              <h4 class="text-muted">{{ courseData.university }}</h4>
              <div class="d-flex flex-lg-row-reverse">
                <p class="text-muted">{{ courseData.school }}</p>
              </div>
            </div>
          </div>
          <p>{{ dataset }}</p>
          <IntakeChart :chartData="intake" v-if="hasIntake"/>
          <SalaryChart :chartData="salary" />
          <EmploymentChart :chartData="employment" />
        </div>
      </div>
    </div>
  </div>
</template>
   
<script>

import IntakeChart from '../components/chart/IntakeChart.vue'
import SalaryChart from '../components/chart/SalaryChart.vue'
import EmploymentChart from '../components/chart/EmploymentChart.vue'


export default {
  name: 'CourseDetails',
  components: {
    IntakeChart,
    SalaryChart,
    EmploymentChart,
  },
  data() {
    return {
      uniName: 'University Name',
      courseName: 'Course Name',
      courseData: '',
      rp: 'N/A',
      grades: 'N/A',
      gpa: 'N/A',
      year: [],
      medianSalary: [],
      employmentRate: [],
      hasIntake: false,
      intake: {
        labels: [],
        datasets: [{
          data: [],
        }],
      },
      salary: {
        labels: [],
        datasets: [
          {
            data: [],
          }
        ],
      },
      employment: {
        labels: [],
        datasets: [
          {
            data: [],
          }
        ],
      },
    }
  },
  mounted() {
    this.uniName = this.$route.params.uniName.replaceAll('-', ' ');
    axios({
      method: 'get',
      url: 'http://localhost:8080/course/ges'
    })
      .then(response => {
        if (response != null) {
          response.data.data.result.records.forEach(element => {
            if (element.university.toUpperCase() === this.$route.params.uniName.replaceAll('-', ' ').toUpperCase())
              if (element.degree.toUpperCase() === this.$route.params.courseName.replaceAll('-', ' ').toUpperCase()) {
                if (element.year == "2019") {
                  this.courseData = element;
                }
                this.year.push(element.year);

                if (element.gross_monthly_median === "na")
                  this.medianSalary.push(0)
                else 
                  this.medianSalary.push(parseInt(element.gross_monthly_median, 10))

                if (element.employment_rate_overall === "na")
                  this.employmentRate.push(0)
                else
                  this.employmentRate.push(parseInt(element.employment_rate_overall, 10))
              }
          });
          this.salary.labels = this.year
          this.salary.datasets[0].data = this.medianSalary
          this.employment.labels = this.year
          this.employment.datasets[0].data = this.employmentRate
        }
      }).catch((error) => {
      })

    axios({
      method: 'get',
      url: 'http://localhost:8080/courseIGP/' + this.$route.params.courseName.replaceAll('-', ' ')
    })
      .then(response => {
        if (response != null) {
          if (response.data.success) {
            if (response.data.data.rankPoint) {
              this.rp = response.data.data.rankPoint;
            }
            if (response.data.data.grades) {
              this.grades = response.data.data.grades;
            }
            if (response.data.data.gpa) {
              this.gpa = response.data.data.gpa;
            }
          }
        }
      }).catch((error) => {
      })

    axios({
      method: 'get',
      url: 'http://localhost:8080/courseIntake/' + this.$route.params.courseName.replaceAll('-', ' ')
    })
      .then(response => {
        if (response != null) {
          if (response.data.success) {
            response.data.data.forEach(element => {
              this.intake.labels.push(element.year)
              this.intake.datasets[0].data.push(element.intake);
            })
            this.hasIntake = true
          }
        }
      }).catch((error) => {
      })

  },
}
</script>

<style scoped>

</style>