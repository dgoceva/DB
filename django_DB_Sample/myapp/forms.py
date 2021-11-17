from django import forms
from myapp.models import Name


class NameForm(forms.ModelForm):
    name_value = forms.CharField(max_length=100, help_text="Enter a name")

    class Meta:
        model = Name
        fields = ('name_value',)
