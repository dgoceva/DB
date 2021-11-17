from django.shortcuts import render
from myapp.models import Name

# Create your views here.


def index(request):
    names_from_db = Name.objects.all()
    context_dict = {'names_from_context': names_from_db}
    return render(request, 'index.html', context_dict)
