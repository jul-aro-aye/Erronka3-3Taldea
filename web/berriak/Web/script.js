$(document).ready(function () {
  let currentPage = 1
  $(document).ready(function () {
    $('#cart-dropdown').hide()
    closeLogin()
    closeNav()
  })

  function loadNews (page) {
    $.ajax({
      url: 'KargatuBerriak.php?page=1',
      type: 'GET',
      data: { page: page },
      dataType: 'json',
      success: function (data) {
        if (data.length > 0) {
          data.forEach(function (news) {
            $('#news-container').append(`
                              <div class="news-item">
                                  <h3>${news.title}</h3>
                                  <p>${news.description}</p>
                                  <img src="${news.image_url}" alt="" style="width:100%;">
                              </div>
                          `)
          })
        } else {
          $('#load-more').hide()
        }
      },
      error: function () {
        alert('Ezin izan dira kargatu berriak.')
      }
    })
  }

  loadNews(currentPage)

  $('#load-more').click(function () {
    currentPage++
    loadNews(currentPage)
  })
  let currentPageShop = 1
  let searchQuery = ''
  let cart = JSON.parse(localStorage.getItem('cart')) || {}

  function loadShop (page, search = '') {
    $.ajax({
      url: 'KargatuProduktuak.php',
      type: 'GET',
      data: { page: page, search: search },
      dataType: 'json',
      success: function (data) {
        if (page === 1) {
          $('#prod-container').empty()
        }

        if (data.length > 0) {
          data.forEach(function (produktuak) {
            let productHtml = `
                          <div class="prod-item" data-id="${produktuak.ProduktuId}">
                              <h3>${produktuak.ProduktuIzena}</h3>
                              <p>Mota: ${produktuak.ProduktuMota}</p>
                              <p>Egoera: ${produktuak.ProduktuEgoera}</p>
                              <p>Prezioa: ${produktuak.ProduktuPrezioa}€</p>
                              <p>Stock: ${produktuak.ProduktuKantitatea}</p>
                              <img src="${produktuak.ProduktuIruId}" alt="" style="width:100%;">`

            if (isLoggedIn === 'true') {
              productHtml += `
                              <button class="erosaski buyNow" data-id="${produktuak.ProduktuId}" data-name="${produktuak.ProduktuIzena}">
                                  Erosi
                              </button>
                              <button class="erosaski add-to-cart" data-id="${produktuak.ProduktuId}" data-name="${produktuak.ProduktuIzena}">
                                  Saskira gehitu
                              </button>
                          `
            }

            productHtml += `</div>`
            $('#prod-container').append(productHtml)
          })
          $('.add-to-cart').click(function () {
            let productId = $(this).data('id')
            let productName = $(this).data('name')
            addToCart(productId, productName)
          })

          $('.buyNow').click(function () {
            let productId = $(this).data('id')
            let productName = $(this).data('name')
            addToCart(productId, productName)
            goToCheckout()
          })
        } else {
          if (page === 1) {
            $('#prod-container').html('<p>Ez da produkturik aurkitu.</p>')
          }
          $('#load-more-prod').hide()
        }
      },
      error: function () {
        alert('Ezin izan dira kargatu produktuak.')
      }
    })
  }

  $('#search-button').click(function () {
    searchQuery = $('#search-input').val()
    currentPageShop = 1
    loadShop(currentPageShop, searchQuery)
  })

  function updateCartIcon () {
    let totalItems = Object.values(cart).reduce(
      (acc, item) => acc + item.quantity,
      0
    )
    $('#cart-count').text(totalItems)
  }

  function showCartDropdown () {
    let cartList = $('#cart-items')
    cartList.empty()

    if (Object.keys(cart).length === 0) {
      cartList.append('<li>Saskia hutsik dago</li>')
    } else {
      Object.keys(cart).forEach(productId => {
        let item = cart[productId]
        cartList.append(`
                  <li>
                      ${item.name} x${item.quantity}
                      <button class="remove-from-cart" data-id="${productId}"><i class='fas fa-minus'></i></button>
                  </li>
              `)
      })

      cartList.append(`
              <li>
                  <button id="clear-cart" class="clear-cart">🗑️ Saskia garbitu</button>
              </li>
          `)

      $('.remove-from-cart')
        .off('click')
        .on('click', function () {
          let productId = $(this).data('id')
          removeFromCart(productId)
        })

      $('#clear-cart').off('click').on('click', clearCart)

      $('.itxi').off('click').on('click', clearCart)
    }

    $('#cart-dropdown').toggle()
  }

  function addToCart (productId, productName) {
    if (!cart[productId]) {
      cart[productId] = { name: productName, quantity: 0 }
    }
    cart[productId].quantity++
    localStorage.setItem('cart', JSON.stringify(cart))
    updateCartIcon()
  }

  function removeFromCart (productId) {
    if (cart[productId]) {
      cart[productId].quantity--
      if (cart[productId].quantity <= 0) {
        delete cart[productId]
      }
      localStorage.setItem('cart', JSON.stringify(cart))
      updateCartIcon()
      showCartDropdown()
    }
  }

  function clearCart () {
    cart = {}
    localStorage.removeItem('cart')
    updateCartIcon()
    showCartDropdown()
  }

  function goToCheckout () {
    let cartData = encodeURIComponent(JSON.stringify(cart))
    window.location.href = 'erosi.php?cart=' + cartData
  }

  $('#cart-icon').click(showCartDropdown)
  $('#checkout-button').click(goToCheckout)

  updateCartIcon()

  function buyNow (productId) {
    let tempCart = {}
    tempCart[productId] = { name: productId, quantity: 1 }

    localStorage.setItem('cart', JSON.stringify(tempCart))

    window.location.href = 'erosi.php'
  }

  loadShop(currentPageShop)

  $('#load-more-prod').click(function () {
    currentPageShop++
    loadShop(currentPageShop)
  })

  const urlParams = new URLSearchParams(window.location.search)
  if (urlParams.has('clearCart') && urlParams.get('clearCart') === 'true') {
    clearCart()
  }
})

function toggleSearch () {
  let searchBar = document.getElementById('search-bar')
  searchBar.classList.toggle('active')

  if (searchBar.classList.contains('active')) {
    searchBar.focus()
  } else {
    searchBar.blur()
  }
}
var mediaqueryList = window.matchMedia('(min-width: 759px)')

function openNav () {
  if (mediaqueryList.matches) {
    document.getElementById('mySidebar').style.width = '100%'
  } else {
    document.getElementById('mySidebar').style.width = '75%'
  }
}

function closeNav () {
  document.getElementById('mySidebar').style.width = '0'
}

var mediaqueryList = window.matchMedia('(min-width: 759px)')
mediaqueryList.addListener(function (EventoMediaQueryList) {
  if (EventoMediaQueryList.matches) {
    document.getElementById('mySidebar').style.width = '100%'
  } else {
    document.getElementById('mySidebar').style.width = '0'
  }
})
var mediaqueryList = window.matchMedia('(min-width: 759px)')
function openLogin () {
  if (mediaqueryList.matches) {
    document.getElementById('login').style.width = '25%'
  } else {
    document.getElementById('login').style.width = '75%'
  }
}

function closeLogin () {
  document.getElementById('login').style.width = '0'
}

mediaqueryList.addListener(function (EventoMediaQueryList) {
  if (EventoMediaQueryList.matches) {
    document.getElementById('login').style.width = '100%'
  } else {
    document.getElementById('login').style.width = '0'
  }
})
