from django.urls import path

from . import views

urlpatterns = [
    path('', views.employee_form, name='employee_insert'),
    path('<int:id>/', views.employee_form, name='employee_update'),
    path('list/', views.employee_list, name='employee_list'),
]
