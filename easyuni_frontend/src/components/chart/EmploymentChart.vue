<template>
    <div class="py-3">
        <h3>GES Employement Rate</h3>
        <small class="text-muted text-small">Data provided by <a href="https://data.gov.sg/" target="_blank">Data.gov.sg</a></small>
        <BarChart :chartData="chartData" :chartOptions="chartOptions" />
    </div>
</template>

<script>
import BarChart from './BarChart'

export default {
    name: 'EmploymentChart',
    components: {
        BarChart,
    },
    props: {
        chartData: {
            type: Object,
            required: true,
        },
    },
    data() {
        return {
            chartOptions: {
                maintainAspectRatio: false,
                responsive: true,
                plugins: {
                    legend: {
                        display: false
                    },
                    tooltip: {
                        callbacks: {
                            label: function(value) {
                                console.log(value)
                                return ' ' + value.formattedValue + '% Employment Rate';
                            }
                        }
                    }
                },
                fill: true,
                backgroundColor: 'rgba(95, 183, 139, 0.75)',
                tension: 0.4,
                pointBorderWidth: 2,
                borderColor: "rgba(18, 253, 136, 1)",
                pointBackgroundColor: "rgba(255, 255, 255, 1)",
                pointRadius: 5,
                pointHoverRadius: 10,
                pointHoverBackgroundColor: "rgba(255, 255, 255, 0)",
                scales: {
                    y: {
                        ticks: {
                            callback: function (value, index, ticks) {
                                return value + '%';
                            }
                        },
                        offset: true,
                        min: 0,
                        max: 100,
                        grid: {
                            display: true,
                        },
                    },
                    x: {
                        grid: {
                            display: false,
                        },
                    }
                }
            },
        }
    },
}
</script>