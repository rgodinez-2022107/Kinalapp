/**
 * KINALAPP - DASHBOARD SCRIPT
 * Funcionalidades interactivas y AJAX
 */

// ============================================
// VARIABLES GLOBALES
// ============================================
const API = {
    clientes: '/clientes',
    productos: '/productos',
    ventas: '/ventas'
};

// ============================================
// FUNCIONES DE CLIENTES
// ============================================

/**
 * Editar cliente - Redirige al formulario con datos precargados
 * @param {string} dpi - DPI del cliente a editar
 */
function editarCliente(dpi) {
    mostrarLoading();
    window.location.href = `/clientes/editar?dpi=${encodeURIComponent(dpi)}`;
}

/**
 * Eliminar cliente con confirmación y petición AJAX
 * @param {string} dpi - DPI del cliente a eliminar
 */
async function eliminarCliente(dpi) {
    const confirmado = await confirmarAccion('¿Estás seguro de eliminar este cliente? Esta acción no se puede deshacer.');

    if (confirmado) {
        mostrarLoading();

        try {
            const response = await fetch(`${API.clientes}/${dpi}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                mostrarNotificacion('Cliente eliminado exitosamente', 'success');
                setTimeout(() => {
                    window.location.href = '/?seccion=clientes';
                }, 1000);
            } else {
                const error = await response.text();
                mostrarNotificacion(`Error: ${error}`, 'error');
                ocultarLoading();
            }
        } catch (error) {
            console.error('Error:', error);
            mostrarNotificacion('Error de conexión al servidor', 'error');
            ocultarLoading();
        }
    }
}

// ============================================
// FUNCIONES DE PRODUCTOS
// ============================================

/**
 * Editar producto - Redirige al formulario con datos precargados
 * @param {number} codigo - Código del producto a editar
 */
function editarProducto(codigo) {
    mostrarLoading();
    window.location.href = `/productos/editar?codigo=${codigo}`;
}

/**
 * Eliminar producto con confirmación y petición AJAX
 * @param {number} codigo - Código del producto a eliminar
 */
async function eliminarProducto(codigo) {
    const confirmado = await confirmarAccion('¿Estás seguro de eliminar este producto? Se perderán todos los datos asociados.');

    if (confirmado) {
        mostrarLoading();

        try {
            const response = await fetch(`${API.productos}/${codigo}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                mostrarNotificacion('Producto eliminado exitosamente', 'success');
                setTimeout(() => {
                    window.location.href = '/?seccion=productos';
                }, 1000);
            } else {
                const error = await response.text();
                mostrarNotificacion(`Error: ${error}`, 'error');
                ocultarLoading();
            }
        } catch (error) {
            console.error('Error:', error);
            mostrarNotificacion('Error de conexión al servidor', 'error');
            ocultarLoading();
        }
    }
}

// ============================================
// FUNCIONES DE VENTAS
// ============================================

/**
 * Ver detalle de venta
 * @param {number} codigo - Código de la venta
 */
function verDetalleVenta(codigo) {
    // Puedes implementar un modal o redirigir a una página de detalle
    mostrarNotificacion(`Venta #${codigo} - Funcionalidad en desarrollo`, 'info');
    // window.location.href = `/ventas/${codigo}`;
}

/**
 * Anular venta con confirmación
 * @param {number} codigo - Código de la venta a anular
 */
async function anularVenta(codigo) {
    const confirmado = await confirmarAccion('¿Anular esta venta? Esto cambiará su estado y no podrá ser revertido fácilmente.');

    if (confirmado) {
        mostrarLoading();

        try {
            const response = await fetch(`${API.ventas}/${codigo}/anular`, {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                mostrarNotificacion('Venta anulada exitosamente', 'success');
                setTimeout(() => {
                    window.location.reload();
                }, 1000);
            } else {
                const error = await response.text();
                mostrarNotificacion(`Error: ${error}`, 'error');
                ocultarLoading();
            }
        } catch (error) {
            console.error('Error:', error);
            mostrarNotificacion('Error de conexión al servidor', 'error');
            ocultarLoading();
        }
    }
}

// ============================================
// FUNCIONES DE UTILIDAD
// ============================================

/**
 * Mostrar loading overlay
 */
function mostrarLoading() {
    let overlay = document.querySelector('.loading-overlay');

    if (!overlay) {
        overlay = document.createElement('div');
        overlay.className = 'loading-overlay';
        overlay.innerHTML = `
            <div class="loading-spinner">
                <i class="fas fa-circle-notch fa-spin fa-3x"></i>
                <p>Cargando...</p>
            </div>
        `;
        document.body.appendChild(overlay);

        // Agregar estilos dinámicamente
        const style = document.createElement('style');
        style.textContent = `
            .loading-overlay {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.7);
                backdrop-filter: blur(4px);
                z-index: 9999;
                display: flex;
                align-items: center;
                justify-content: center;
                animation: fadeIn 0.3s ease;
            }
            .loading-spinner {
                text-align: center;
                background: rgba(30, 41, 59, 0.9);
                padding: 2rem;
                border-radius: 20px;
                backdrop-filter: blur(8px);
            }
            .loading-spinner i {
                color: #3B82F6;
            }
            .loading-spinner p {
                margin-top: 1rem;
                color: #E2E8F0;
            }
            @keyframes fadeIn {
                from { opacity: 0; }
                to { opacity: 1; }
            }
        `;
        document.head.appendChild(style);
    }

    overlay.style.display = 'flex';
}

/**
 * Ocultar loading overlay
 */
function ocultarLoading() {
    const overlay = document.querySelector('.loading-overlay');
    if (overlay) {
        overlay.style.display = 'none';
    }
}

/**
 * Mostrar notificación temporal
 * @param {string} mensaje - Mensaje a mostrar
 * @param {string} tipo - Tipo de notificación (success, error, info)
 */
function mostrarNotificacion(mensaje, tipo = 'info') {
    // Verificar si ya existe una notificación
    let notificacion = document.querySelector('.toast-notification');

    if (notificacion) {
        notificacion.remove();
    }

    // Crear nueva notificación
    notificacion = document.createElement('div');
    notificacion.className = `toast-notification toast-${tipo}`;

    const iconos = {
        success: 'fa-check-circle',
        error: 'fa-exclamation-circle',
        info: 'fa-info-circle'
    };

    notificacion.innerHTML = `
        <i class="fas ${iconos[tipo] || iconos.info}"></i>
        <span>${mensaje}</span>
    `;

    document.body.appendChild(notificacion);

    // Agregar estilos
    const style = document.createElement('style');
    style.textContent = `
        .toast-notification {
            position: fixed;
            top: 20px;
            right: 20px;
            padding: 1rem 1.5rem;
            border-radius: 12px;
            display: flex;
            align-items: center;
            gap: 12px;
            z-index: 10000;
            animation: slideInRight 0.3s ease;
            box-shadow: 0 4px 12px rgba(0,0,0,0.3);
            font-weight: 500;
        }
        .toast-success {
            background: rgba(16, 185, 129, 0.95);
            border-left: 4px solid #10B981;
            color: white;
        }
        .toast-error {
            background: rgba(239, 68, 68, 0.95);
            border-left: 4px solid #EF4444;
            color: white;
        }
        .toast-info {
            background: rgba(59, 130, 246, 0.95);
            border-left: 4px solid #3B82F6;
            color: white;
        }
        @keyframes slideInRight {
            from {
                transform: translateX(100%);
                opacity: 0;
            }
            to {
                transform: translateX(0);
                opacity: 1;
            }
        }
    `;
    document.head.appendChild(style);

    // Auto-ocultar después de 3 segundos
    setTimeout(() => {
        notificacion.style.animation = 'slideOutRight 0.3s ease';
        setTimeout(() => {
            notificacion.remove();
        }, 300);
    }, 3000);
}

/**
 * Confirmar acción con diálogo personalizado
 * @param {string} mensaje - Mensaje de confirmación
 * @returns {Promise<boolean>} - Promise que resuelve a true/false
 */
function confirmarAccion(mensaje) {
    return new Promise((resolve) => {
        // Crear modal personalizado
        const modal = document.createElement('div');
        modal.className = 'confirm-modal';
        modal.innerHTML = `
            <div class="confirm-modal-content">
                <i class="fas fa-question-circle"></i>
                <p>${mensaje}</p>
                <div class="confirm-buttons">
                    <button class="btn btn-outline confirm-no">Cancelar</button>
                    <button class="btn btn-primary confirm-yes">Confirmar</button>
                </div>
            </div>
        `;

        // Estilos del modal
        const style = document.createElement('style');
        style.textContent = `
            .confirm-modal {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.8);
                backdrop-filter: blur(8px);
                z-index: 10001;
                display: flex;
                align-items: center;
                justify-content: center;
                animation: fadeIn 0.2s ease;
            }
            .confirm-modal-content {
                background: #1E293B;
                padding: 2rem;
                border-radius: 28px;
                text-align: center;
                max-width: 400px;
                animation: scaleIn 0.2s ease;
            }
            .confirm-modal-content i {
                font-size: 3rem;
                color: #F59E0B;
                margin-bottom: 1rem;
            }
            .confirm-modal-content p {
                margin-bottom: 1.5rem;
                font-size: 1.1rem;
            }
            .confirm-buttons {
                display: flex;
                gap: 1rem;
                justify-content: center;
            }
            @keyframes scaleIn {
                from {
                    transform: scale(0.9);
                    opacity: 0;
                }
                to {
                    transform: scale(1);
                    opacity: 1;
                }
            }
        `;
        document.head.appendChild(style);
        document.body.appendChild(modal);

        // Manejar eventos
        modal.querySelector('.confirm-yes').onclick = () => {
            modal.remove();
            resolve(true);
        };

        modal.querySelector('.confirm-no').onclick = () => {
            modal.remove();
            resolve(false);
        };

        // Cerrar al hacer clic fuera
        modal.onclick = (e) => {
            if (e.target === modal) {
                modal.remove();
                resolve(false);
            }
        };
    });
}

// ============================================
// INICIALIZACIÓN
// ============================================

/**
 * Inicializar eventos cuando el DOM esté listo
 */
document.addEventListener('DOMContentLoaded', () => {
    // Agregar efecto de hover a las filas de la tabla
    const tableRows = document.querySelectorAll('.data-table tbody tr');
    tableRows.forEach(row => {
        row.addEventListener('click', (e) => {
            // No hacer nada si se hizo clic en un botón
            if (!e.target.closest('.icon-btn')) {
                // Puedes agregar funcionalidad adicional aquí
                console.log('Fila clickeada');
            }
        });
    });

    // Auto-ocultar alertas después de 5 segundos
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            alert.style.animation = 'slideOut 0.3s ease';
            setTimeout(() => {
                alert.remove();
            }, 300);
        }, 5000);
    });

    console.log('Dashboard inicializado correctamente');
});

// Agregar animación de salida
const styleSheet = document.createElement('style');
styleSheet.textContent = `
    @keyframes slideOut {
        from {
            opacity: 1;
            transform: translateX(0);
        }
        to {
            opacity: 0;
            transform: translateX(-20px);
        }
    }
    @keyframes slideOutRight {
        from {
            transform: translateX(0);
            opacity: 1;
        }
        to {
            transform: translateX(100%);
            opacity: 0;
        }
    }
`;
document.head.appendChild(styleSheet);