<?php
class Pages extends Controller
{
    public function __construct()
    {
        // echo 'Pages loaded';

    }

    public function index()
    {

        $data = [
            'title' => 'TraversyMVC'
        ];


        $this->view('pages/index', $data);
    }
    public function about()
    {
        $data = [
            'title' => 'About Us'
        ];

        $this->view('pages/about', $data);
    }
}