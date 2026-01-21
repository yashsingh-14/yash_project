document.addEventListener("DOMContentLoaded", function () {

    // 1. Initialize Lenis (Smooth Scrolling)
    const lenis = new Lenis({
        duration: 1.2,
        easing: (t) => Math.min(1, 1.001 - Math.pow(2, -10 * t)),
        direction: 'vertical',
        gestureDirection: 'vertical',
        smooth: true,
        mouseMultiplier: 1,
        smoothTouch: false,
        touchMultiplier: 2,
    });

    function raf(time) {
        lenis.raf(time);
        requestAnimationFrame(raf);
    }
    requestAnimationFrame(raf);

    // 2. Initialize Vanilla-Tilt (3D Hover Effects)
    VanillaTilt.init(document.querySelectorAll(".card"), {
        max: 15, // Max tilt angle
        speed: 400, // Speed of the effect
        glare: true, // Enable glare
        "max-glare": 0.5, // Opacity of glare
        scale: 1.05 // Zoom on hover
    });

    // Also tilt hero content slightly for depth
    VanillaTilt.init(document.querySelector(".slide-content"), {
        max: 5,
        speed: 1000,
        glare: false,
        scale: 1.0
    });

    // 3. GSAP Animations & ScrollTrigger
    gsap.registerPlugin(ScrollTrigger);

    // Navbar Glass Effect
    // Navbar Smart Scroll Effect
    const navbar = document.querySelector('.navbar');
    let lastScrollY = window.scrollY;

    window.addEventListener('scroll', () => {
        const currentScrollY = window.scrollY;

        // Add frosted glass effect when not at top
        if (currentScrollY > 50) {
            navbar.classList.add('scrolled');
        } else {
            navbar.classList.remove('scrolled');
        }

        // Hide/Show Logic
        if (currentScrollY > lastScrollY && currentScrollY > 100) {
            // Scrolling Down -> Hide
            navbar.style.transform = 'translateY(-100%)';
        } else {
            // Scrolling Up -> Show
            navbar.style.transform = 'translateY(0)';
        }

        lastScrollY = currentScrollY;
    });

    // Section Titles Animation
    gsap.utils.toArray('.section-title').forEach(title => {
        gsap.from(title, {
            scrollTrigger: {
                trigger: title,
                start: "top 80%",
                toggleActions: "play none none reverse"
            },
            y: 50,
            opacity: 0,
            duration: 1,
            ease: "power3.out"
        });
    });

    // Cards Staggered Animation
    gsap.utils.toArray('.cards-container').forEach(container => {
        gsap.from(container.children, {
            scrollTrigger: {
                trigger: container,
                start: "top 85%",
            },
            y: 100,
            opacity: 0,
            duration: 0.8,
            stagger: 0.2, // Stagger effect for premium feel
            ease: "back.out(1.7)"
        });
    });

    // 4. Page Transition (Reveal & Exit)
    const transitionOverlay = document.createElement('div');
    transitionOverlay.classList.add('page-transition');
    document.body.appendChild(transitionOverlay);

    // Function to hide overlay (reveal page)
    function revealPage() {
        gsap.to(transitionOverlay, {
            opacity: 0,
            duration: 0.5,
            ease: "power2.out",
            onComplete: () => {
                transitionOverlay.style.display = 'none';
                transitionOverlay.style.pointerEvents = 'none'; // Ensure clicks pass through
            }
        });
    }

    // Reveal on initial load
    revealPage();

    // Handle Back/Forward Cache (Browser Back Button)
    window.addEventListener('pageshow', (event) => {
        if (event.persisted) {
            transitionOverlay.style.opacity = '0';
            transitionOverlay.style.display = 'none';
            revealPage();
        }
    });

    // Handle Link Clicks for Transition
    document.querySelectorAll('a').forEach(link => {
        link.addEventListener('click', function (e) {
            const href = this.getAttribute('href');

            // Ignore if:
            // 1. New tab
            // 2. Hash link (anchor)
            // 3. Javascript:void
            // 4. External link (optional)
            if (this.target === '_blank' ||
                href.startsWith('#') ||
                href.includes('javascript') ||
                href === '') {
                return;
            }

            e.preventDefault();

            // Show overlay (Exit)
            transitionOverlay.style.display = 'block';
            transitionOverlay.style.pointerEvents = 'all'; // Block clicks during transition

            gsap.to(transitionOverlay, {
                opacity: 1,
                duration: 0.4,
                ease: "power2.in",
                onComplete: () => {
                    window.location.href = href;
                }
            });

            // Safety timeout: If navigation fails, hide overlay after 2 seconds
            setTimeout(() => {
                if (window.location.href === window.location.href) { // If still on same page
                    revealPage();
                }
            }, 3000);
        });
    });


    // Hero Slider Logic (unchanged logic, just ensuring it runs)
    const slides = document.querySelectorAll('.slide');
    const dots = document.querySelectorAll('.slider-dot');
    let currentSlide = 0;

    if (slides.length > 0) {
        function showSlide(n) {
            slides.forEach(slide => slide.classList.remove('active'));
            if (dots.length > 0) dots.forEach(dot => dot.classList.remove('active'));

            currentSlide = (n + slides.length) % slides.length;
            slides[currentSlide].classList.add('active');
            if (dots.length > 0) dots[currentSlide].classList.add('active');

            // Re-trigger text animations by removing/adding dom nodes or just class toggle handled by CSS
        }

        setInterval(() => {
            showSlide(currentSlide + 1);
        }, 6000);

        showSlide(0);
    }

});