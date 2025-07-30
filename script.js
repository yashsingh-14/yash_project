  // Navbar Scroll Effect
        window.addEventListener('scroll', function() {
            const navbar = document.querySelector('.navbar');
            if (window.scrollY > 50) {
                navbar.classList.add('scrolled');
            } else {
                navbar.classList.remove('scrolled');
            }
        });

        // Mobile Menu Toggle
        const menuBtn = document.querySelector('.mobile-menu-btn');
        const navLinks = document.querySelector('.nav-links');

        menuBtn.addEventListener('click', function() {
            navLinks.classList.toggle('active');
        });

        // Hero Slider
        const slides = document.querySelectorAll('.slide');
        const dots = document.querySelectorAll('.slider-dot');
        const prevBtn = document.querySelector('.prev');
        const nextBtn = document.querySelector('.next');
        let currentSlide = 0;
        let slideInterval;

        function showSlide(n) {
            slides.forEach(slide => slide.classList.remove('active'));
            dots.forEach(dot => dot.classList.remove('active'));
            
            currentSlide = (n + slides.length) % slides.length;
            slides[currentSlide].classList.add('active');
            dots[currentSlide].classList.add('active');
        }

        function nextSlide() {
            showSlide(currentSlide + 1);
        }

        function prevSlide() {
            showSlide(currentSlide - 1);
        }

        function startSlider() {
            slideInterval = setInterval(nextSlide, 5000);
        }

        function stopSlider() {
            clearInterval(slideInterval);
        }

        // Event Listeners
        nextBtn.addEventListener('click', function() {
            nextSlide();
            stopSlider();
            startSlider();
        });

        prevBtn.addEventListener('click', function() {
            prevSlide();
            stopSlider();
            startSlider();
        });

        dots.forEach((dot, index) => {
            dot.addEventListener('click', function() {
                showSlide(index);
                stopSlider();
                startSlider();
            });
        });

        // Initialize Slider
        showSlide(0);
        startSlider();

        // Testimonial Slider
        const testimonials = document.querySelector('.testimonials-container');
        const testimonialDots = document.querySelectorAll('.testimonial-dot');
        let currentTestimonial = 0;

        function showTestimonial(n) {
            testimonials.style.transform = `translateX(-${n * 100}%)`;
            testimonialDots.forEach(dot => dot.classList.remove('active'));
            testimonialDots[n].classList.add('active');
            currentTestimonial = n;
        }

        function nextTestimonial() {
            currentTestimonial = (currentTestimonial + 1) % testimonialDots.length;
            showTestimonial(currentTestimonial);
        }

        testimonialDots.forEach((dot, index) => {
            dot.addEventListener('click', () => showTestimonial(index));
        });

        // Auto-rotate testimonials
        setInterval(nextTestimonial, 6000);
        
        // Popular Selections Tabs
        const tabBtns = document.querySelectorAll('.tab-btn');
        const tabContents = document.querySelectorAll('.tab-content');
        
        tabBtns.forEach(btn => {
            btn.addEventListener('click', () => {
                // Remove active class from all buttons and contents
                tabBtns.forEach(btn => btn.classList.remove('active'));
                tabContents.forEach(content => content.classList.remove('active'));
                
                // Add active class to clicked button and corresponding content
                btn.classList.add('active');
                const tabId = btn.getAttribute('data-tab');
                document.getElementById(tabId).classList.add('active');
            });
        });
        
        // Add to cart functionality
        const addToCartBtns = document.querySelectorAll('.add-to-cart');
        addToCartBtns.forEach(btn => {
            btn.addEventListener('click', function() {
                const card = this.closest('.card');
                const title = card.querySelector('h3').textContent;
                const price = card.querySelector('.current-price').textContent;
                
                // In a real app, you would add to cart logic here
                alert(`Added to cart: ${title} - ${price}`);
                
                // Visual feedback
                this.textContent = '✓';
                this.style.background = '#2ed573';
                setTimeout(() => {
                    this.textContent = '+';
                    this.style.background = '#f1f2f6';
                }, 1000);
            });
        });